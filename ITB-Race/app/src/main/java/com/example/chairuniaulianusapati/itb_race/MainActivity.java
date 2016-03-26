package com.example.chairuniaulianusapati.itb_race;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView response;
    Button buttonStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.startButton);
        response = (TextView) findViewById(R.id.responseTextView);
        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                response.setText("");
                Client myClient = new Client("167.205.34.132", 3111, response, getApplicationContext());
                myClient.execute();
            }
        });
    }

    /** Called when the user clicks the Start button */
    /*public void requestLocation(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/
}
