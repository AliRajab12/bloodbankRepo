package com.example.ali.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DeleteAccount extends AppCompatActivity {
    String g = "";
    EditText e1, e2;
    String user_name, user_pass;
    Button delete;
AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        Intent i = getIntent();
        g = i.getStringExtra("return");
        e1 = findViewById(R.id.editText6);
        e2 = findViewById(R.id.editText7);
        delete = findViewById(R.id.button17);
        Toast.makeText(DeleteAccount.this,g,Toast.LENGTH_LONG).show();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = e1.getText().toString();
                user_pass = e2.getText().toString();
                if (g.equals("toMain")) {
                    String type = "frombank";//for delete from table donor
                    new deleteTask().execute(type, user_name, user_pass);
                } else {
                    String type = "frombank2";//for delete from table Needly
                    new deleteTask().execute(type, user_name, user_pass);
                }
            }
        });
    }

    public class deleteTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            dialog = new AlertDialog.Builder(DeleteAccount.this).create();
            dialog.setTitle("Delete Status");
        }

        @Override
        protected String doInBackground(String... voids) {
            String type = voids[0];
            String user_name = voids[1];
            String user_pass = voids[2];
            if(type.equals("frombank")){
              //  String delete_url = "http://192.168.43.93/project3/deletefrombank.php";
                String delete_url = "https://hegelian-tops.000webhostapp.com/deletefrombank.php";
                try {
                    return Delete(delete_url, user_name, user_pass);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(type.equals("frombank2")){
              //  String delete_url = "http://192.168.43.93/project3/deletefrombank2.php";
                String delete_url = "https://hegelian-tops.000webhostapp.com/deletefrombank2.php";
                try {
                    return Delete(delete_url, user_name, user_pass);
                } catch (IOException e) {
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
    private String Delete(String delete_url, String username, String userpass) throws IOException {
        URL url = new URL(delete_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream out = con.getOutputStream();
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(userpass, "UTF-8");
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

