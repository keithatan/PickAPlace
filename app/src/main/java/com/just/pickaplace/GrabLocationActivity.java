package com.just.pickaplace;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

//import android.location.Location;
//import android.location.LocationManager;

public class GrabLocationActivity extends AppCompatActivity {

    double longitude;
    double latitude;
    Button nextButton;
    String location;
    private FusedLocationProviderClient mFusedLocationClient;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab_location);
        nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(listener);
        location = "New York City, NYC, 350 5th Ave, New York, NY 10118";
        /*LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        */
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Logic to handle location object
                            System.out.println("cant find userlocation");
                        } else {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                });

    }
    //Retrieve the long,lat, keywords
    //
    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( GrabLocationActivity.this, GetWalkingDistance.class);

            Bundle extras = new Bundle();
            extras.putString("latitude", "" + latitude);
            extras.putString("longitude", "" + longitude);
            extras.putString("location", location);
            intent.putExtras(extras);
            startActivity(intent);
        }
    };


}
