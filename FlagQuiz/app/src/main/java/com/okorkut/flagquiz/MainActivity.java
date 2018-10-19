package com.okorkut.flagquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.okorkut.flagquiz.common.Common;
import com.okorkut.flagquiz.dbhelper.DBHelper;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView txtMode;
    Button btnPlay, btnScore;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        txtMode = findViewById(R.id.txtMode);
        btnPlay = findViewById(R.id.btnPlay);
        btnScore = findViewById(R.id.btnScore);

        dbHelper = new DBHelper(this);

        try{
            dbHelper.createDatabase();

        }catch (Exception e){
            Log.e("MainActivity Erro", e.getMessage());
            e.printStackTrace();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0){
                    txtMode.setText(Common.MODE.EASY.toString());
                }else if(progress == 1){
                    txtMode.setText(Common.MODE.MEDIUM.toString());
                }else if(progress == 2){
                    txtMode.setText(Common.MODE.HARD.toString());
                }else if(progress == 3){
                    txtMode.setText(Common.MODE.HARDEST.toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Playing.class);

                intent.putExtra("MODE", getPlayMode()); // send mode to playing page

                startActivity(intent);

                finish();
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Score.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public String getPlayMode(){

        if (seekBar.getProgress() == 0){
            return Common.MODE.EASY.toString();
        } else {
            return txtMode.getText().toString();
        }
    }
}
