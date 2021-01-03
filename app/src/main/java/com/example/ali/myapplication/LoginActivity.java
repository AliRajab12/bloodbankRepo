package com.example.ali.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText name,pass;
    private Button log,reg;
    Context ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        log=(Button)findViewById(R.id.button);
        reg=(Button)findViewById(R.id.button2);

    }

    public void login(View view) {
        String username=name.getText().toString();
        String userpass=pass.getText().toString();
        String type="login";
        RegisterAndLogin b=new RegisterAndLogin(this);
        b.execute(type,username,userpass);
        name.setText("");
        pass.setText("");
    }}