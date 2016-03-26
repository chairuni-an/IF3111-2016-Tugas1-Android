package com.example.chairuniaulianusapati.itb_race;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, SensorEventListener {

    private GoogleMap mMap;
    private ImageView image;
    private float currentDegree = 0f;
    private SensorManager mSensorManager;
    JSONObject json;
    LatLng destination = new LatLng(-6.8915, 107.6107); //itb

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        image = (ImageView) findViewById(R.id.imageViewCompass);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Bundle bundle = getIntent().getExtras();
        try {
            json = new JSONObject(bundle.getString("response"));
            if(json.getString("status").equals("wrong_answer")){
                String latitude = bundle.getString("latitude");
                String longitude = bundle.getString("longitude");
                destination = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            }

        }catch (JSONException e){}

        Context context = getApplicationContext();
        CharSequence text = "Response: " + json.toString();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();
        if (orientation == 1) {
            /* The device is rotated to the left. */
            degree += 90;
            Log.v("Left", "Rotated Left");
        } else if (orientation == 3) {
            /* The device is rotated to the right. */
            degree -= 90;
            Log.v("Right", "Rotated Right");
        } else {

        }

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Destination and move the camera and zoom
        try{
            destination = new LatLng(json.getDouble("latitude"), json.getDouble("longitude"));
        }catch(JSONException e){}
        mMap.addMarker(new MarkerOptions().position(destination).title("Marker in Destination"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 16.0f));
    }

    /** Called when the user clicks the Submit Answer button */
    public void answerButtonClicked(View view) {
        Intent intent = new Intent(this, SubmitAnswerActivity.class);
        intent.putExtra("response", json.toString());
        startActivity(intent);
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    public void cameraButtonClicked(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean success = takePictureIntent.resolveActivity(getPackageManager()) != null;
        Log.i("MapsActivity", "Camera Opened");
        if (success){
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }
}
