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
    EditText valueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_walking_distance);

        valueEditText = findViewById(R.id.editText);

        Intent intent = getIntent();
        globalInformation = intent.getExtras();
        System.out.print(globalInformation);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (valueEditText.getText().toString().trim().length() <= 0) {
                travelDistance = 40000;
            }
            else {
                double travelMile = Double.parseDouble(valueEditText.getText().toString());
                travelDistance = (int) (travelMile * 1610);
            }

            Intent intent = new Intent( GetWalkingDistance.this, GeneratePlacesActivity.class);

            if (travelDistance > 40000){
                travelDistance = 40000;
            }
            else if( travelDistance <= 5){
                travelDistance = 100;
            }
            else {
                travelDistance = 40000;
            }

            globalInformation.putString("radius", Integer.toString(travelDistance));
            intent.putExtras(globalInformation);
            System.out.print(globalInformation);
            startActivity(intent);
        }
    };




}
