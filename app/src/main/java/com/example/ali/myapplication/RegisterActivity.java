package com.example.ali.myapplication;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Location l;
    EditText nam, pass, age, ph;
    TextView t1, t2;
    Spinner sp, sp2, sp3;
    Button bt, goToMainActivity, goToInformationActivity, goToSettingActivity;
    String b_group = "", state = "", area = "";
    String longi = "";
    String lati = "";
    String g = "";
    String g2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gpsListener gpsListener = new gpsListener(getApplicationContext());
        l = gpsListener.getLocation();
        if (l != null) {
            longi = Double.toString(l.getLongitude());
            lati = Double.toString(l.getLatitude());
        }
        Intent i = getIntent();
        g = i.getStringExtra("k");
        g2=i.getStringExtra("return");
        Toast.makeText(this, g, Toast.LENGTH_LONG).show();
        t1 = (TextView) findViewById(R.id.textView2);
        t2 = (TextView) findViewById(R.id.textView3);
        nam = (EditText) findViewById(R.id.editText3);
        pass = (EditText) findViewById(R.id.editText4);
        age = (EditText) findViewById(R.id.editText5);
        ph = findViewById(R.id.editText);
        sp = (Spinner) findViewById(R.id.spinner);
        sp2 = findViewById(R.id.spinner2);
        sp3 = findViewById(R.id.spinner3);
        goToMainActivity = findViewById(R.id.button);
        bt = (Button) findViewById(R.id.button3);
        goToInformationActivity = (Button) findViewById(R.id.button7);
        goToSettingActivity = findViewById(R.id.button6);
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


        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText("Longitude: " + longi + " / Latitude: " + lati);
            }
        });
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(g2.equals("toMain")){
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }else{ Intent i = new Intent(RegisterActivity.this, NeededActivity.class);
                    startActivity(i);}
            }
        });
        goToInformationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, InformationActivity.class);
                i.putExtra("k",g);
                i.putExtra("return",g2);
                startActivity(i);
            }
        });
        goToSettingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, SettingActivity.class);
                i.putExtra("k",g);
                i.putExtra("return",g2);
                startActivity(i);
            }
        });


    }


    public void onreg(View view) {

        String name = nam.getText().toString();
        String password = pass.getText().toString();
        String age_user = age.getText().toString();
        String phone = ph.getText().toString();
        if (g.equals("Donor")) {//for insert in donor table (bank)
            String type = "register";
            RegisterAndLogin b = new RegisterAndLogin(this);
            if ((longi != "") && (lati != "") && (name != "") && (password != "") && (age_user != "") && (phone != "")) {
                b.execute(type, name, password, age_user, phone, b_group, state, area, longi, lati);
            } else {
                Toast.makeText(getApplicationContext(), "Please input information", Toast.LENGTH_LONG).show();
            }
        }
        if (g.equals("need")) {//for insert in Needly table (bank2)
            String type = "need";
            RegisterAndLogin b = new RegisterAndLogin(this);
            if ((longi != "") && (lati != "") && (name != "") && (password != "") && (age_user != "") && (phone != "")) {
                b.execute(type, name, password, age_user, phone, b_group, state, area, longi, lati);
            } else {
                Toast.makeText(getApplicationContext(), "Please input information", Toast.LENGTH_LONG).show();
            }
        }
    }
}

