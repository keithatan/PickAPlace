package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startButton = findViewById(R.id.startSearch);
        startButton.setOnClickListener(listener);

        preferences = findViewById(R.id.preferences);
        preferences.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(MainActivity.this, GrabLocationActivity.class);
            Intent intent2 = new Intent(MainActivity.this, PreferencesActivity.class);

            /*
            Bundle extras = new Bundle();
            extras.putString("Question", edit1.getText().toString());
            extras.putString("Question2",edit2.getText().toString());
            extras.putString("Answer", textView.getText().toString());
            extras.putString("Answer2",textView2.getText().toString());

            intent.putExtras(extras);
            */


            switch(v.getId()) {
                case R.id.button:
                    startActivity(intent1);
                    break;
                case R.id.preferences:
                    startActivity(intent2);
                    break;
            }
        }
    };
}
