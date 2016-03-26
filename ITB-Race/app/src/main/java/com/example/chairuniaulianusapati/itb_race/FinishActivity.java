package com.example.chairuniaulianusapati.itb_race;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class FinishActivity extends AppCompatActivity {
    JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Bundle bundle = getIntent().getExtras();
        try {
            json = new JSONObject(bundle.getString("response"));
        }catch (JSONException e){}

        Context context = getApplicationContext();
        CharSequence text = "Response: " + json.toString();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
