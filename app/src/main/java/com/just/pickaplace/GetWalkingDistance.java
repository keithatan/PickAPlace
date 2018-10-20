package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetWalkingDistance extends AppCompatActivity {


    Bundle globalInformation;
    int travelDistance;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_walking_distance);
        Intent intent = getIntent();
        globalInformation = intent.getExtras();

        System.out.print(globalInformation);

        travelDistance = 5;

        btn = findViewById(R.id.button);
        btn.setOnClickListener(listener);

    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( GetWalkingDistance.this, GetWalkingDistance.class);

            globalInformation.putString("radius", Integer.toString(travelDistance));

            intent.putExtras(globalInformation);
            System.out.print(globalInformation);
            startActivity(intent);
        }
    };

}
