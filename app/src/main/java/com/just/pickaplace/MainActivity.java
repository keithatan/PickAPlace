package com.just.pickaplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.button);
        startButton.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, GrabLocationActivity.class);
            /*
            Bundle extras = new Bundle();
            extras.putString("Question", edit1.getText().toString());
            extras.putString("Question2",edit2.getText().toString());
            extras.putString("Answer", textView.getText().toString());
            extras.putString("Answer2",textView2.getText().toString());

            intent.putExtras(extras);
            */
            startActivity(intent);
        }
    };
}
