package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChosenActivity extends AppCompatActivity {

    Bundle globalInformation;
    TextView mName;
    TextView mCost;
    TextView mRating;
    TextView mPhone;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen);
        Intent i = getIntent();
        globalInformation = i.getExtras();

        mName = findViewById(R.id.tName);
        mCost = findViewById(R.id.tCost);
        mRating = findViewById(R.id.tRating);
        mPhone = findViewById(R.id.tPhone);

        mName.setText(globalInformation.getString("chosenName"));
        mCost.setText(globalInformation.getString("chosenCost"));
        mRating.setText(globalInformation.getString("chosenRating"));
        mPhone.setText(globalInformation.getString("chosenPhone"));

    }
}
