package com.example.chairuniaulianusapati.itb_race;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SubmitAnswerActivity extends AppCompatActivity {

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
        categories.add("CC Barat"); //code: cc_barat
        categories.add("CC Timur"); //code: cc_timur
        categories.add("DPR"); //code: dpr
        categories.add("GKU Barat"); //code: gku_barat
        categories.add("GKU Timur"); //code: gku_timur
        categories.add("Intel"); //code: intel
        categories.add("Kubus"); //code: kubus
        categories.add("PAU"); //code: pau
        categories.add("Perpustakaan"); //code: perpustakaan
        categories.add("Sunken"); //code: sunken

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }


}
