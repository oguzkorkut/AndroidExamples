package com.okorkut.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText nameET;
    EditText passwordET;
    Button sendBtn;
    RadioGroup genderRG;

    String name, password, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("LifeCycle", "onCreate");

    }

    private void definition(){
        nameET = findViewById(R.id.name);
        passwordET = findViewById(R.id.password);
        sendBtn = findViewById(R.id.nextBtn);
        genderRG = findViewById(R.id.genderGroup);

    }

    private void getFormData(){
        name = nameET.getText().toString();
        password = passwordET.getText().toString();
        gender = ((RadioButton)findViewById(genderRG.getCheckedRadioButtonId())).getText().toString();
    }

    public void nextActivity(View view){

        getFormData();

        StringBuilder builder = new StringBuilder("");

        builder.append("Activity1->").append("name:").append(name).append(" ").append("Password:").append(password).append(" ").append("Gender:").append(gender);
        Log.i(  "Form Data :" , builder.toString() );

        //Intent intent = new Intent(this, Main2Activity.class);

        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

        intent.putExtra( "name", name);
        intent.putExtra("password",password);
        intent.putExtra("gender", gender);

        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LifeCycle", "onStart");

        definition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LifeCycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LifeCycle", "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LifeCycle", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LifeCycle", "onDestroy");
    }
}
