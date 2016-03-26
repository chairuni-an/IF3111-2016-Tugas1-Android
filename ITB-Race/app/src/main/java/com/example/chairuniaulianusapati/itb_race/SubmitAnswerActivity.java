package com.example.chairuniaulianusapati.itb_race;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubmitAnswerActivity extends AppCompatActivity {
    Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        /*categories.add("CC Barat"); //code: cc_barat
        categories.add("CC Timur"); //code: cc_timur
        categories.add("DPR"); //code: dpr
        categories.add("GKU Barat"); //code: gku_barat
        categories.add("GKU Timur"); //code: gku_timur
        categories.add("Intel"); //code: intel
        categories.add("Kubus"); //code: kubus
        categories.add("PAU"); //code: pau
        categories.add("Perpustakaan"); //code: perpustakaan
        categories.add("Sunken"); //code: sunken*/

        categories.add("cc_barat"); //code: cc_barat
        categories.add("cc_timur"); //code: cc_timur
        categories.add("dpr"); //code: dpr
        categories.add("gku_barat"); //code: gku_barat
        categories.add("gku_timur"); //code: gku_timur
        categories.add("intel"); //code: intel
        categories.add("kubus"); //code: kubus
        categories.add("pau"); //code: pau
        categories.add("perpustakaan"); //code: perpustakaan
        categories.add("sunken"); //code: sunken

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        buttonSubmit = (Button) findViewById(R.id.submitButton);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                JSONObject json = new JSONObject();
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                String answer = (String) spinner.getSelectedItem();
                try {
                    Bundle bundle = getIntent().getExtras();
                    JSONObject dummyjson = new JSONObject(bundle.getString("response"));
                    json.put("com", "answer");
                    json.put("nim", dummyjson.getString("nim"));
                    json.put("answer", answer);
                    json.put("longitude", dummyjson.getString("longitude"));
                    json.put("latitude", dummyjson.getString("latitude"));
                    json.put("token", dummyjson.getString("token"));
                    Log.i("SubmitAnswerActivity", "Message: " + json.toString());
                    //{“com”:”answer”,”nim”:”13512999”,”answer”:”labtek_v”, ”longitude”:”6.234123132”,”latitude”:”0.1234123412”,”token”:”21nu2f2n3rh23diefef23hr23ew”}
                } catch (org.json.JSONException e) {
                    // nothing
                }
                Client myClient = new Client(json, "167.205.34.132", 3111, getApplicationContext());
                myClient.execute();
            }
        });


    }

    public void submitButtonClicked(View view) {
        Intent intent = new Intent(this, SubmitAnswerActivity.class);
        startActivity(intent);
    }


}
