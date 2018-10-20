package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GrabLocationActivity extends AppCompatActivity {

    String longitude;
    String latitude;
    Button nextButton;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab_location);
        nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(listener);
        location = "New York City, NYC, 350 5th Ave, New York, NY 10118";
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( GrabLocationActivity.this, GetWalkingDistance.class);

            Bundle extras = new Bundle();
            extras.putString("latitude", latitude);
            extras.putString("longitude", longitude);
            extras.putString("location", location);
            intent.putExtras(extras);
            startActivity(intent);
        }
    };


}
