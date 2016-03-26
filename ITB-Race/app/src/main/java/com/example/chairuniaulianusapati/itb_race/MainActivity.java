package com.example.chairuniaulianusapati.itb_race;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.startButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                JSONObject json = new JSONObject();
                try{
                    json.put("com", "req_loc");
                    json.put("nim", "13513054");
                }catch(org.json.JSONException e){
                    // nothing
                }
                Client myClient = new Client(json, "167.205.34.132", 3111, getApplicationContext());
                myClient.execute();
            }
        });
    }

}
