package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetWalkingDistance extends AppCompatActivity {


    Bundle globalInformation;
    Button btn;
    int travelDistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_walking_distance);

        EditText valueEditText = findViewById(R.id.editText);
        double travelMile = Double.parseDouble(valueEditText.getText().toString());
        travelDistance = (int) (travelMile * 1610);

        Intent intent = getIntent();
        globalInformation = intent.getExtras();
        System.out.print(globalInformation);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent( GetWalkingDistance.this, GeneratePlacesActivity.class);

            if (travelDistance > 40000){
                travelDistance = 40000;
            }
            else if( travelDistance <= 0){
                travelDistance = 5;
            }

            globalInformation.putString("radius", Integer.toString(travelDistance));
            intent.putExtras(globalInformation);
            System.out.print(globalInformation);
            startActivity(intent);
        }
    };




}
