package com.example.ali.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Enable_GPS extends AppCompatActivity {

    Context ct;
    Button open, next, goToMainActivity, goToInformationActivity, goToSettingActivity;
    TextView text;
    String g = "";
    String g2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable__gps);
        Intent i = getIntent();
        g = i.getStringExtra("k");
        g2 = i.getStringExtra("return");
        Toast.makeText(Enable_GPS.this,g,Toast.LENGTH_LONG).show();
        ActivityCompat.requestPermissions(Enable_GPS.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 123);
        next = (Button) findViewById(R.id.button5);
        text = (TextView) findViewById(R.id.textView);
        open = (Button) findViewById(R.id.button4);
        goToMainActivity = findViewById(R.id.button);
        goToInformationActivity = findViewById(R.id.button7);
        goToSettingActivity = findViewById(R.id.button6);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setEnabled(true);
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (g2.equals("toNeed")) {
                    Intent i = new Intent(Enable_GPS.this, NeededActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(Enable_GPS.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
        goToInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Enable_GPS.this, InformationActivity.class);
                i.putExtra("k", g);
                i.putExtra("return", g2);
                startActivity(i);
            }
        });
        goToSettingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Enable_GPS.this, SettingActivity.class);
                i.putExtra("k", g);
                i.putExtra("return", g2);
                startActivity(i);
            }
        });
    }

    public void goToRegister(View view) {
        Intent i2 = new Intent(Enable_GPS.this, RegisterActivity.class);
        i2.putExtra("k", g);
        i2.putExtra("return", g2);
        startActivity(i2);
    }

}
