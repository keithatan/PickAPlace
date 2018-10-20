package com.just.pickaplace;

import android.Manifest;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PermissionsActivity extends AppCompatActivity {
    private int callbackResult = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        int locationPermissions;
        boolean permission = false;
//        while(!permission) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, callbackResult);
//            locationPermissions = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
//            SystemClock.sleep(5000);
//            if(locationPermissions == 1) {
//                permission = true;
//            }
//
//        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, callbackResult);
        Intent locationGrab = new Intent(PermissionsActivity.this, GrabLocationActivity.class);
        startActivity(locationGrab);

    }
}
