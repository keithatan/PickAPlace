package com.just.pickaplace;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChosenActivity extends AppCompatActivity {

    Bundle globalInformation;
    TextView mName;
    TextView mCost;
    TextView mRating;
    TextView mPhone;
    TextView mCat;

    Button yelpBtn;

    TextView mAddress;

//    TextView mAdd;
//    TextView mCity;
//    TextView mZip;
//    TextView mState;
    String address;

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
        mCat = findViewById(R.id.tCat);

//        mAdd = findViewById(R.id.tAdd);
//        mCity = findViewById(R.id.tCity);
//        mZip = findViewById(R.id.tZip);
//        mState = findViewById(R.id.tState);

        mAddress = findViewById(R.id.tAddress);
        address = globalInformation.getString("chosenAdd") + ", " + globalInformation.getString("chosenCity") + ", " + globalInformation.getString("chosenState");

        mName.setText(globalInformation.getString("chosenName"));
        mCost.setText(globalInformation.getString("chosenCost"));
        mRating.setText(globalInformation.getString("chosenRating"));
        mPhone.setText(globalInformation.getString("chosenPhone"));
        mCat.setText(globalInformation.getString("chosenCat"));
        mAddress.setText(address);

        yelpBtn = findViewById(R.id.button2);

        yelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(globalInformation.getString("chosenURL")));
                startActivity(mIntent);
            }
        });

//        mCity.setText(globalInformation.getString("chosenCity"));
//        mZip.setText(globalInformation.getString("chosenZip"));
//        mState.setText(globalInformation.getString("chosenState"));
           }

    public void maps (View v) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    public void yelp (View v) {
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.purdue.edu"));
        startActivity(mIntent);
    }
    public void home (View v) {
        Intent intent1 = new Intent(ChosenActivity.this, MainActivity.class);
        startActivity(intent1);
    }
}
