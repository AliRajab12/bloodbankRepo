package com.example.ali.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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

public class RegisterAndLogin extends AsyncTask<String, String, String> {
    Context ctx;
    AlertDialog dialog;

    RegisterAndLogin(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(ctx).create();
        dialog.setTitle("Login Status");
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url = "https://hegelian-tops.000webhostapp.com/login2.php";
     //   String login_url = "http://192.168.43.93/project3/login2.php";

      //  String register_url = "https://hegelian-tops.000webhostapp.com/register.php";
        String type = params[0];
        if (type.equals("login")) {
            String username = params[1];
            String userpass = params[2];
            try {
                return Login(login_url, username, userpass);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register")) {
           // String register_url = "http://192.168.43.93/project3/register.php";
            String register_url = "https://hegelian-tops.000webhostapp.com/register.php";
            String user_name = params[1];
            String user_pass = params[2];
            String user_age = params[3];
            String user_phone = params[4];
            String group_blood = params[5];
            String user_state=params[6];
            String user_area=params[7];
            String loc_longi = params[8];
            String loc_lati = params[9];
         //   String user_kind = params[10];
            try {
                return Regeister(register_url, user_name, user_pass, user_age, user_phone, group_blood,user_state,user_area, loc_longi, loc_lati);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("need")) {
       //     String register_url = "http://192.168.43.93/project3/registerbank2.php";
            String register_url = "https://hegelian-tops.000webhostapp.com/registerbank2.php";
            String user_name = params[1];
            String user_pass = params[2];
            String user_age = params[3];
            String user_phone = params[4];
            String group_blood = params[5];
            String user_state = params[6];
            String user_area = params[7];
            String loc_longi = params[8];
            String loc_lati = params[9];
        //    String user_kind = params[10];
            try {
                return Regeister(register_url, user_name, user_pass, user_age, user_phone, group_blood, user_state, user_area, loc_longi, loc_lati);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            return null;
    }


    @Override
    protected void onPostExecute(String result) {
        dialog.setMessage(result);
        dialog.show();
        if (result.equals("Welcome")) {
            Intent i = new Intent(ctx, SearchActivity.class);
            ctx.startActivity(i);
        }


    }

    @NonNull
    private String Login(String login_url, String username, String userpass) throws IOException {
        URL url = new URL(login_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream out = con.getOutputStream();
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" + URLEncoder.encode("userpass", "UTF-8") + "=" + URLEncoder.encode(userpass, "UTF-8");
        bf.write(post_data);
        bf.flush();
        bf.close();
        out.close();
        String result = GetResultRegister(con);
        con.disconnect();
        return result;
    }

    @NonNull
    private String Regeister(String register_url, String user_name, String user_pass, String user_age, String user_phone, String group_blood, String user_state,String user_area,String loc_longi, String loc_lati) throws IOException {
        URL url = new URL(register_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        OutputStream out = con.getOutputStream();
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") +
                "&" + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8") +
                "&" + URLEncoder.encode("user_age", "UTF-8") + "=" + URLEncoder.encode(user_age, "UTF-8") +
                "&" + URLEncoder.encode("user_phone", "UTF-8") + "=" + URLEncoder.encode(user_phone, "UTF-8") +
                "&" + URLEncoder.encode("group_blood", "UTF-8") + "=" + URLEncoder.encode(group_blood, "UTF-8") +
                "&" + URLEncoder.encode("user_state", "UTF-8") + "=" + URLEncoder.encode(user_state, "UTF-8") +
                "&" + URLEncoder.encode("user_area", "UTF-8") + "=" + URLEncoder.encode(user_area, "UTF-8") +
                "&" + URLEncoder.encode("loc_longi", "UTF-8") + "=" + URLEncoder.encode(loc_longi, "UTF-8") +
                "&" + URLEncoder.encode("loc_lati", "UTF-8") + "=" + URLEncoder.encode(loc_lati, "UTF-8");
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



