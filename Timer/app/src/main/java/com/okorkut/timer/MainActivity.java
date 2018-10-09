package com.okorkut.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        CountDownTimer finish = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                textView.setText("Left " + l / 1000);
            }

            @Override
            public void onFinish() {

                Toast.makeText(getApplicationContext(), "Time's done", Toast.LENGTH_SHORT).show();
                textView.setText("Left :0");
            }
        }.start();
    }
}
