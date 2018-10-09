package com.okorkut.catchthekenny;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreTextView;
    TextView timeTextView;

    ImageView imageView00;
    ImageView imageView01;
    ImageView imageView02;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;

    ImageView[] imageViews;


    Runnable runnable;
    Handler handler;

    int score;

    boolean isFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.textView);

        score = 0;

        isFinish = false;

        imageView00 =  findViewById(R.id.imageView00);
        imageView01 =  findViewById(R.id.imageView01);
        imageView02 =  findViewById(R.id.imageView02);
        imageView10 =  findViewById(R.id.imageView10);
        imageView11 =  findViewById(R.id.imageView11);
        imageView12 =  findViewById(R.id.imageView12);
        imageView20 =  findViewById(R.id.imageView20);
        imageView21 =  findViewById(R.id.imageView21);
        imageView22 =  findViewById(R.id.imageView22);


        imageViews = new ImageView[]{imageView00,imageView01,imageView02,imageView10,imageView11,imageView12,imageView20,imageView21,imageView22};

        hideImages();

        imageView00 = findViewById(R.id.imageView00);

        CountDownTimer countDownTimer = new CountDownTimer(5000, 2000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timeTextView.setText("Time'a Off");

                handler.removeCallbacks(runnable);

                isFinish = true;
            }
        }.start();

    }

    public void increaseScore(View view){

        if (!isFinish){
            score++;

            scoreTextView.setText("Score:" + score);
        } else {
            Toast.makeText(this,"Game's finished",Toast.LENGTH_SHORT).show();
        }

    }

    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageViews){
                    image.setVisibility(View.INVISIBLE);
                }

                Random r = new Random();

                int i = r.nextInt(8-0);

                imageViews[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);
            }
        };

        handler.post(runnable);


    }
}
