package com.example.ali.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView t1, t2, t3,t4,t5,t6,Long;
Button loc;
String name;
Double Lati,Longi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        t1 = findViewById(R.id.textView10);
        t2 = findViewById(R.id.textView12);
        t3 = findViewById(R.id.textView14);
        t4 = findViewById(R.id.textView11);
        t5 = findViewById(R.id.textView13);
        t6 = findViewById(R.id.textView15);
        loc=findViewById(R.id.button15);
Long=findViewById(R.id.textView9);
        Intent i = getIntent();
         name = i.getStringExtra("name");
        String group = i.getStringExtra("group");
        String phone = i.getStringExtra("phone");
        String Longitude=i.getStringExtra("Longitude");
        String Latitude=i.getStringExtra("Latitude");
         Longi=Double.parseDouble(Longitude);
         Lati=Double.parseDouble(Latitude);
        t1.setText(name);
        t2.setText(group);
        t3.setText(phone);
       // Long.setText(Longitude+"\t"+Latitude);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String label=name;
                String uriBegin="geo:"+Lati +","+Longi;
                String query=Lati + "," +Longi + "("+ label +")";
                String encodeQuery= Uri.encode(query);
                String uriStirng=uriBegin+ "?q="+encodeQuery +"&z=16";
                Uri uri=Uri.parse(uriStirng);
                Intent mapIntent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(mapIntent);
            }
        });


    }
}
