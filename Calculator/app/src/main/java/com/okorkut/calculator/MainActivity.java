package com.okorkut.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText1 = null;
    EditText editText2 = null;

    TextView resultText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);


        resultText = findViewById(R.id.textView);

        resultText.setText("Result:");
    }

    public void sum(View view){

        if (!isEmpty()){
            int a = Integer.parseInt(editText1.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());

            int c = a + b;

            resultText.setText("Result:" + c);
        }
    }

    public void deduct(View view){
        if (!isEmpty()){
            int a = Integer.parseInt(editText1.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());

            int c = a - b;

            resultText.setText("Result:" + c);
        }
    }

    public void multiply(View view){
        if (!isEmpty()){
            int a = Integer.parseInt(editText1.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());

            int c = a * b;

            resultText.setText("Result:" + c);
        }
    }

    public  void divide(View view){
        if (!isEmpty()){
            int a = Integer.parseInt(editText1.getText().toString());
            int b = Integer.parseInt(editText2.getText().toString());

            int c = a / b;

            resultText.setText("Result:" + c);
        }
    }

    public boolean isEmpty(){
        if (editText1.getText().toString().matches("") || editText2.getText().toString().matches("")){
            resultText.setText("Numara yok!");
            return true;
        } else {
            return false;
        }
    }
}
