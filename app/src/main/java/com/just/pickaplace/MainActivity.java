package com.just.pickaplace;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button preferences;
    public int locationPermissions;
    private int callbackResult = 0;

    public static final int GRANTED = 0;
    public static final int DENIED = 1;
    public static final int BLOCKED_OR_NEVER_ASKED = 2;
    public static int value2 = -3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startSearch);
        startButton.setOnClickListener(listener);

        preferences = findViewById(R.id.preferences);
        preferences.setOnClickListener(listener);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, callbackResult);
        value2 = getPermissionStatus(MainActivity.this, ACCESS_COARSE_LOCATION);
        Log.i("GRANTED VALUED", "" + value2);

        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, callbackResult);
        //locationPermissions  = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public static int getPermissionStatus(Activity activity, String androidPermissionName) {
        if(ContextCompat.checkSelfPermission(activity, androidPermissionName) != PackageManager.PERMISSION_GRANTED) {
            if(!ActivityCompat.shouldShowRequestPermissionRationale(activity, androidPermissionName)){
                return BLOCKED_OR_NEVER_ASKED;
            }
            return DENIED;
        }
        return GRANTED;
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(MainActivity.this, GrabLocationActivity.class);
            Intent intent2 = new Intent(MainActivity.this, PermissionsActivity.class);

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, callbackResult);
            value2 = getPermissionStatus(MainActivity.this, ACCESS_COARSE_LOCATION);
            Log.i("GRANTED VALUED", "" + value2);
            if (value2 == 0) {
                switch (v.getId()) {
                    case R.id.startSearch:
                        startActivity(intent1);
                        break;
                    case R.id.preferences:
                        break;
                }
            }
        }
    };
}