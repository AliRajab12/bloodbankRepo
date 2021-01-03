package com.example.ali.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    Button goToMainActivity, goToRegisterActivity, goToInformationActivity, showMyInformation, goToDeleteAccount,goToUpdateInforamtion,ShowInfo;
    String g = "", g2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        goToMainActivity = findViewById(R.id.button);
        goToRegisterActivity = findViewById(R.id.button2);
        goToInformationActivity = findViewById(R.id.button7);
        showMyInformation = findViewById(R.id.button11);
        goToDeleteAccount = findViewById(R.id.button12);
        goToUpdateInforamtion=findViewById(R.id.button16);
        Intent i = getIntent();
        g = i.getStringExtra("return");
        g2 = i.getStringExtra("k");
        ShowInfo=findViewById(R.id.button11);
        ShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this).create();
                dialog.setTitle("من نحن؟");
                dialog.setMessage(Html.fromHtml("<font color='red'>"+"Developer:A.I.S.H Team"+"<br>"+"ALBAATH UNIVERSITY"+"</font>"));
                dialog.show();

            }
        });
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (g.equals("toMain")) {
                    Intent i = new Intent(SettingActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SettingActivity.this, NeededActivity.class);
                    startActivity(i);
                }
            }
        });
        goToRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, Enable_GPS.class);
                i.putExtra("return", g);
                i.putExtra("k", g2);
                startActivity(i);
            }
        });
        goToInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, InformationActivity.class);
                i.putExtra("return", g);
                i.putExtra("k", g2);
                startActivity(i);
            }
        });
        goToDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingActivity.this, DeleteAccount.class);
                i.putExtra("return", g);
                i.putExtra("k", g2);
                startActivity(i);
            }
        });
        goToUpdateInforamtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SettingActivity.this,UpdateInforamtion.class);
                i.putExtra("return",g);
                i.putExtra("k",g2);
                startActivity(i);
            }
        });

    }
}
