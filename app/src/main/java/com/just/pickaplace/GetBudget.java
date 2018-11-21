package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class GetBudget extends AppCompatActivity {
    Button startButton;

    ToggleButton hoursOpen;
    ToggleButton hoursClosed;

    ToggleButton budgetAll;
    ToggleButton budget1;
    ToggleButton budget2;
    ToggleButton budget3;
    ToggleButton budget4;

    ToggleButton rating1;
    ToggleButton rating2;
    ToggleButton rating3;

    ToggleButton foodAll;
    ToggleButton food1;
    ToggleButton food2;
    ToggleButton food3;
    ToggleButton food4;
    ToggleButton food5;
    ToggleButton food6;
    ToggleButton food7;
    ToggleButton food8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_budget);
        startButton = findViewById(R.id.buttonNext);

//        hoursOpen = findViewById(R.id.toggleButton1a);
//        hoursClosed = findViewById(R.id.toggleButton1b);
//        hoursOpen.setOnCheckedChangeListener(changeChecker);
//        hoursClosed.setOnCheckedChangeListener(changeChecker);

        budgetAll = findViewById(R.id.toggleButton3a);
        budget1 = findViewById(R.id.toggleButton3b);
        budget2 = findViewById(R.id.toggleButton3c);
        budget3 = findViewById(R.id.toggleButton3d);
        budget4 = findViewById(R.id.toggleButton3e);
        budgetAll.setOnCheckedChangeListener(changeChecker);
        budget1.setOnCheckedChangeListener(changeChecker);
        budget2.setOnCheckedChangeListener(changeChecker);
        budget3.setOnCheckedChangeListener(changeChecker);
        budget4.setOnCheckedChangeListener(changeChecker);

        rating1 = findViewById(R.id.toggleButton4a);
        rating2 = findViewById(R.id.toggleButton4b);
        rating3 = findViewById(R.id.toggleButton4c);
        rating1.setOnCheckedChangeListener(changeChecker);
        rating2.setOnCheckedChangeListener(changeChecker);
        rating3.setOnCheckedChangeListener(changeChecker);

        foodAll = findViewById(R.id.toggleButton5a);
        food1 = findViewById(R.id.toggleButton5b);
        food2 = findViewById(R.id.toggleButton5c);
        food3 = findViewById(R.id.toggleButton5d);
        food4 = findViewById(R.id.toggleButton5e);
        food5 = findViewById(R.id.toggleButton5f);
        food6 = findViewById(R.id.toggleButton5g);
        food7 = findViewById(R.id.toggleButton5h);
        food8 = findViewById(R.id.toggleButton5i);
        foodAll.setOnCheckedChangeListener(changeChecker);
        food1.setOnCheckedChangeListener(changeChecker);
        food2.setOnCheckedChangeListener(changeChecker);
        food3.setOnCheckedChangeListener(changeChecker);
        food4.setOnCheckedChangeListener(changeChecker);
        food5.setOnCheckedChangeListener(changeChecker);
        food6.setOnCheckedChangeListener(changeChecker);
        food7.setOnCheckedChangeListener(changeChecker);
        food8.setOnCheckedChangeListener(changeChecker);
    }

    CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (buttonView == hoursOpen) {
                    hoursClosed.setChecked(false);
                }
                else if (buttonView == hoursClosed) {
                    hoursOpen.setChecked(false);
                }

                if (buttonView == budgetAll) {
                    budget1.setChecked(false);
                    budget2.setChecked(false);
                    budget3.setChecked(false);
                    budget4.setChecked(false);
                }
                else if (buttonView == budget1 || buttonView == budget2 || buttonView == budget3 || buttonView == budget4) {
                    budgetAll.setChecked(false);
                }

                if (buttonView == rating1) {
                    rating2.setChecked(false);
                    rating3.setChecked(false);
                }
                else if (buttonView == rating2) {
                    rating1.setChecked(false);
                    rating3.setChecked(false);
                }
                else if (buttonView == rating3) {
                    rating1.setChecked(false);
                    rating2.setChecked(false);
                }

                if (buttonView == foodAll) {
                    food1.setChecked(false);
                    food2.setChecked(false);
                    food3.setChecked(false);
                    food4.setChecked(false);
                    food5.setChecked(false);
                    food6.setChecked(false);
                    food7.setChecked(false);
                    food8.setChecked(false);
                }
                else if (buttonView == food1 || buttonView == food2 || buttonView == food3 || buttonView == food4 || buttonView == food5 || buttonView == food6 || buttonView == food7 || buttonView == food8) {
                    foodAll.setChecked(false);
                }

            }
        }
    };

    public void cancelFilters(View v) {
        finish();
    }
    public void applyFilters(View v) {
        finish();
    }
}
