package com.okorkut.flagquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.okorkut.flagquiz.dbhelper.DBHelper;
import com.okorkut.flagquiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class Playing extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL = 1000;// 1 sn
    final static long TIMEOUT = 7000; // 7 sn

    int progressBarValue = 0;

    CountDownTimer mCountDown; //for  progressbar
    List<Question> questions = new ArrayList<Question>();

    DBHelper dbHelper;

    int score = 0, thisQuestion, totalQuestion, correctAnswer;

    String mode="";

    //Control
    ProgressBar progressBar;
    ImageView flag;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestion;

    int index = 0;

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

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

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

        showQuestion(index);
    }

    public void showQuestion(int i){

        if (index < totalQuestion){
            thisQuestion++;
            txtQuestion.setText(String.format("%d/%d",thisQuestion,totalQuestion));
            progressBar.setProgress(0);
            progressBarValue =0;

            int flagId = this.getResources().getIdentifier(questions.get(i).getImage().toLowerCase(),"drawable",getPackageName());

            flag.setImageResource(flagId);
            btnA.setText(questions.get(i).getAnswerA());
            btnB.setText(questions.get(i).getAnswerB());
            btnC.setText(questions.get(i).getAnswerC());
            btnD.setText(questions.get(i).getAnswerD());

            mCountDown.start();
        } else {
            Intent intent = new Intent(this, Done.class);
            Bundle data = new Bundle();
            data.putInt("SCORE", score);
            data.putInt("TOTAL", totalQuestion);
            data.putInt("CORRECT", correctAnswer);

            intent.putExtras(data);

            startActivity(intent);

            finish();
        }

    }

    @Override
    public void onClick(View v) {

        mCountDown.cancel();

        if (index < totalQuestion){
            Button clickedBtn = (Button)v;
            if (clickedBtn.getText().toString().equalsIgnoreCase(questions.get(index).getCorrectAnswer())){
                score = + 10;
                correctAnswer++;showQuestion(++index);
            }
        }else{
            showQuestion(++index);
        }

        txtScore.setText(String.format("%d",score));
    }
}


