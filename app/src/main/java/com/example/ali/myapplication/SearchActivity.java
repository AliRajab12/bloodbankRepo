package com.example.ali.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class SearchActivity extends AppCompatActivity {
    Button n, goToNewDonor, goToMainActivity;
    private TextView mTextMessage;
    Context c;
    String json_string;
    private ListView lv;
    String s1, s2, s3, s4;
    ArrayAdapter<String> adapter;
    String names[] = new String[1000];
    String GroupBlood[] = new String[1000];
    String Phone[] = new String[1000];
    String Longitude[]=new String[1000];
String Latitude[]=new String[1000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        n = (Button) findViewById(R.id.button3);
        mTextMessage = (TextView) findViewById(R.id.message);
        lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SearchActivity.this, ProfileActivity.class);//for show userInformation in ProfileActivity
                String name = names[position];
                String group = GroupBlood[position];
                String phone = Phone[position];
                String longi=Longitude[position];
                String lati=Latitude[position];
                i.putExtra("name", name);
                i.putExtra("group", group);
                i.putExtra("phone", phone);
                i.putExtra("Longitude",longi);
                i.putExtra("Latitude",lati);
                startActivity(i);
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1){ @Override
        public View getView(int position, View convertView, ViewGroup parent){
            // Cast the list view each item as text view
            TextView item = (TextView) super.getView(position,convertView,parent);
            // Set the list view item's text color
            item.setTextColor(Color.parseColor("#6950C7"));
            //#FF3E80F1
            // Set the item text style to bold
            item.setTypeface(item.getTypeface(), Typeface.BOLD);

            // Change the item text size
            item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

            // return the view
            return item;
        }};
        lv.setAdapter(adapter);
        goToNewDonor = findViewById(R.id.button2);
        goToMainActivity = findViewById(R.id.button);
        Bundle bundle = getIntent().getExtras();
        s1 = bundle.getString("b_group");
        s2 = bundle.getString("state");
        s3 = bundle.getString("area");
        s4 = bundle.getString("k");
        Toast.makeText(SearchActivity.this,s4,Toast.LENGTH_LONG).show();
        adapter.clear();
        new HttpGetTask().execute(s1, s2, s3, s4);
         //   mTextMessage.setText(s1 + " " + s2 + " " + s3);


        goToNewDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, Enable_GPS.class));
            }
        });
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });
    }


    public class HttpGetTask extends AsyncTask<String, Void, String> {

        ProgressDialog p;
        @Override
        protected void onPreExecute() {
            p=new ProgressDialog(SearchActivity.this);
            p.setTitle("Get Information");
            p.setMessage("Loading");
            p.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            p.setProgress(0);
            p.show();

        }

        @Override
        protected String doInBackground(String... voids) {
            String group_blood = voids[0];
            String user_state = voids[1];
            String user_area = voids[2];
            String type = voids[3];
            try {
                return getString(group_blood, user_state, user_area, type);

            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
             //   mTextMessage.setText(result); //for show data in TextView included in SearchActivity
            json_string = result;//for store jsonObjects return from doInBackground
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("lists");
                int count = 0;
                String name, group_blood;//for select Information to show
                while (count < jsonArray.length()) {
                    JSONObject j = jsonArray.getJSONObject(count);//for get jsonObject by index
                    name = j.getString("Name");
                    names[count] = name + "\n";
                    group_blood = j.getString("Group_Blood");
                    GroupBlood[count] = group_blood + "\n";
                    String phone = j.getString("Phone");
                    Phone[count] = phone + "\n";
                    String Longi=j.getString("Longitude");
                    Longitude[count]=Longi + "\n";
                    String Lati=j.getString("Latitude");
                    Latitude[count]=Lati + "\n";
                    String line = "UserName : " + name + " GroupBlood : " + group_blood + " Phone :" + phone;
                    adapter.add(line);

                    count++;

                }
if(count>0){
p.hide();}
else{
                    p.hide();
    AlertDialog dialog = new AlertDialog.Builder(SearchActivity.this).create();
    dialog.setMessage(Html.fromHtml("<font color='red'>"+"No Result"+"</font>"));
    dialog.show();
}
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }


    @NonNull
    private String getString(String group_blood, String user_state, String user_area, String type) throws IOException {
        String json_url = "";
        if (type.equals("need")) {
            //json_url = "http://192.168.43.93/project3/read3.php";
             json_url = "https://hegelian-tops.000webhostapp.com/read3.php";
        } else if (type.equals("Donor")) {
            //json_url = "http://192.168.43.93/project3/readfrombank2.php";
        json_url="https://hegelian-tops.000webhostapp.com/readfrombank2.php";
        }

        String JSON_STRING;
        URL url = new URL(json_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream out = httpURLConnection.getOutputStream();
        BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
        String post_data = URLEncoder.encode("group_blood", "UTF-8") + "=" + URLEncoder.encode(group_blood, "UTF-8") + "&" + URLEncoder.encode("user_state", "UTF-8") + "=" + URLEncoder.encode(user_state, "UTF-8")
                + "&" + URLEncoder.encode("user_area", "UTF-8") + "=" + URLEncoder.encode(user_area, "UTF-8");
        bf.write(post_data);
        bf.flush();
        bf.close();
        out.close();

        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
        StringBuilder stringBuilder = new StringBuilder();
        while ((JSON_STRING = bufferedReader.readLine()) != null) {
            stringBuilder.append(JSON_STRING + "\n");
        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        return stringBuilder.toString().trim();
    }


}

