package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class GetWalkingDistance extends AppCompatActivity {

    Bundle globalInformation;
    int travelDistance;
    EditText valueEditText;

    SeekBar seekBar;
    TextView distanceText;
    double value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_walking_distance);
        seekBar = findViewById(R.id.seekBar);
        distanceText = findViewById(R.id.textView);

        Intent intent = getIntent();
        globalInformation = intent.getExtras();
        System.out.print(globalInformation);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0: value = 0.25; break;
                    case 1: value = 0.5; break;
                    case 2: value = 1; break;
                    case 3: value = 2; break;
                    case 4: value = 3; break;
                    case 5: value = 4; break;
                    case 6: value = 5; break;
                    case 7: value = 10; break;
                    case 8: value = 15; break;
                    case 9: value = 25; break;
                }
                if (progress == 2) {
                    distanceText.setText("Travel Distance: " + (int) value + " Mile");
                }
                else if (progress > 2) {
                    distanceText.setText("Travel Distance: " + (int) value + " Miles");
                }
                else {
                    distanceText.setText("Travel Distance: " + value + " Miles");
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void nextPage(View v) {
        double travelDouble;
        if (value > 15)
            travelDouble = 40000;
        else {
            travelDouble = value * 1610;
        }
        travelDistance = (int) travelDouble;
//        double travelDouble = value * 1610;
//        travelDistance = (int) travelDouble;

//        if (valueEditText.getText().toString().trim().length() <= 0) {
//            travelDistance = 40000;
//        }
//        else {
//            double travelMile = Double.parseDouble(valueEditText.getText().toString());
//            travelDistance = (int) (travelMile * 1610);
//        }
//
//        if (travelDistance > 40000){
//            travelDistance = 40000;
//        }
//        else if( travelDistance <= 5){
//            travelDistance = 100;
//        }

        Intent intent = new Intent( GetWalkingDistance.this, GetBudget.class);

        globalInformation.putString("radius", Integer.toString(travelDistance));
        intent.putExtras(globalInformation);
        System.out.print(globalInformation);
        startActivity(intent);
    }

    public void getFilters(View v){
        Intent intent2 = new Intent(GetWalkingDistance.this, GetBudget.class);
        startActivity(intent2);
    }

}
