package com.example.ali.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NeededActivity extends AppCompatActivity {

    Button b1, b3, b4, goToInformation,goToSetting;
    Spinner sp, sp2, sp3;
    String b_group, state, area;
    TextView t;
static String s="need";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed);
        b1 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button8);
        goToInformation = findViewById(R.id.button7);
        sp = findViewById(R.id.spinner);
        sp2 = findViewById(R.id.spinner2);
        sp3 = findViewById(R.id.spinner3);
goToSetting=findViewById(R.id.button6);
        t=findViewById(R.id.textView17);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.splash);
        t.startAnimation(animation);
goToSetting.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(NeededActivity.this,SettingActivity.class);
        i.putExtra("k","need");
        i.putExtra("return","toNeed");
        startActivity(i);
    }
});
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NeededActivity.this, Enable_GPS.class);
                i.putExtra("k","need");
                i.putExtra("return","toNeed");
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NeededActivity.this, LoginActivity.class);
                i.putExtra("return","toNeed");
                startActivity(i);
            }
        });
        goToInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NeededActivity.this, InformationActivity.class);
                i.putExtra("k","need");
                i.putExtra("return","toNeed");
                startActivity(i);
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                b_group = sp.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = sp2.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = sp3.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NeededActivity.this, SearchActivity.class);
                i.putExtra("b_group", b_group);
                i.putExtra("state", state);
                i.putExtra("area", area);
                i.putExtra("k","need");
                startActivity(i);
            }
        });
    }


}

