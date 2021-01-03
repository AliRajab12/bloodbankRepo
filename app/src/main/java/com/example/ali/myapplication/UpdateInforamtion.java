package com.example.ali.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UpdateInforamtion extends AppCompatActivity {
    String new_name,new_pass,conf_pass,old_pass;
    TextView t1,t2,t3;
    EditText e1,e2,e3,e4;
    Button b1;
String g="";
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inforamtion);
        Intent i = getIntent();
        g = i.getStringExtra("return");
        t1 =(TextView)findViewById(R.id.textView4);
        t2 =(TextView)findViewById(R.id.textView5);
        t3 =(TextView)findViewById(R.id.textView6);
        e1 =(EditText)findViewById(R.id.editText6);
        e2 =(EditText)findViewById(R.id.editText8);
        e3 =(EditText)findViewById(R.id.editText9);
        e4 =(EditText)findViewById(R.id.editText10);
        b1=(Button)findViewById(R.id.button6);

        Toast.makeText(UpdateInforamtion.this,g,Toast.LENGTH_LONG).show();
    }

    public void Update_info(View view) {
        new_name = e1.getText().toString();
        new_pass = e2.getText().toString();
        conf_pass = e3.getText().toString();
        old_pass = e4.getText().toString();
        if (!new_pass.equalsIgnoreCase(conf_pass)) {
            Toast.makeText(getApplicationContext(), "Please input Password valid", Toast.LENGTH_LONG).show();
        } else if (old_pass.equalsIgnoreCase("")) {
            Toast.makeText(getApplicationContext(), "Please input old password", Toast.LENGTH_LONG).show();
        } else {
            if (g.equals("toMain")) {
                String type = "frombank";//for update from table donor
                new back().execute(type, new_name, new_pass, old_pass);
            } else {
                String type = "frombank2";//for update from table Needly
                new back().execute(type, new_name, new_pass, old_pass);
            }

        }

    }

    class back extends AsyncTask<String,String,String>{


        @Override
        protected void onPreExecute() {
            dialog = new AlertDialog.Builder(UpdateInforamtion.this).create();
            dialog.setTitle("Update Status");
        }

        @Override
        protected String doInBackground(String... strings) {
            String type=strings[0];

                String new_name = strings[1];
                String new_pass = strings[2];
                String old_pass = strings[3];
            if (type.equals("frombank")){
                String update_url = "https://hegelian-tops.000webhostapp.com/updatebank.php";
                try {
                    return Update(update_url, new_name, new_pass, old_pass);

                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }else    if (type.equals("frombank2")){
                String update_url = "https://hegelian-tops.000webhostapp.com/updatebank2.php";
                try {
                    return Update(update_url, new_name, new_pass, old_pass);

                }  catch (IOException e) {
                    e.printStackTrace();
                }
            }
return null;
        }
        @Override
        protected void onPostExecute(String result) {
            dialog.setMessage(result);
            dialog.show();}
    }

    @NonNull
    private String Update(String update_url, String new_name, String new_pass, String old_pass) throws IOException {
        URL url = new URL(update_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream out = con.getOutputStream();
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        String post_data = URLEncoder.encode("new_name", "UTF-8") + "=" + URLEncoder.encode(new_name, "UTF-8") +
                "&" + URLEncoder.encode("new_pass", "UTF-8") + "=" + URLEncoder.encode(new_pass, "UTF-8") +
                "&" + URLEncoder.encode("old_pass", "UTF-8") + "=" + URLEncoder.encode(old_pass, "UTF-8");
        bf.write(post_data);
        bf.flush();
        bf.close();
        out.close();
        String result = GetResultRegister(con);
        con.disconnect();
        return result;

    }
    private String GetResultRegister(HttpURLConnection con) throws IOException {
        InputStream in = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
        String result = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            result += line;
        }
        br.close();
        in.close();
        return result;
    }
}
