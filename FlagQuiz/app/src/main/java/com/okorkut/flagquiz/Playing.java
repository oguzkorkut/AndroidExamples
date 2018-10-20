package com.okorkut.flagquiz;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.okorkut.flagquiz.dbhelper.DBHelper;
import com.okorkut.flagquiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Playing extends AppCompatActivity {

    final static long INTERVAL = 1000;// 1 sn
    final static long TIMEOUT = 7000; // 7 sn

    int progressBarValue = 0;

    CountDownTimer mCountDown; //for  progressbar
    List<Question> questions = new ArrayList<Question>();

    DBHelper dbHelper;

    int score = 0, thisQuestion, totalQuestion, correctAnswer;
    final int index = 0;
    String mode="";

    //Control
    ProgressBar progressBar;
    ImageView flag;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        Bundle extra = getIntent().getExtras();

        if (extra != null){
            mode = extra.getString("MODE");
        }

        dbHelper = new DBHelper(getApplicationContext());

        txtScore = findViewById(R.id.txtScore);
        txtQuestion = findViewById(R.id.txtQuestion);
        progressBar = findViewById(R.id.progressBar);

        flag = findViewById(R.id.question_flag);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);

    }

    @Override
    protected void onResume() {
        super.onResume();

        questions = dbHelper.getQuestionMode(mode);

        totalQuestion = questions.size();

        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressBarValue);
                progressBarValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };

        showQuestion();
    }

    public void showQuestion(){

    }

}
