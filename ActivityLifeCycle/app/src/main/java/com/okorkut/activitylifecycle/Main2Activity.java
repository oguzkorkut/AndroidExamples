package com.okorkut.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    String name;
    String password;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getPreviousActivityValues();
    }

    public void previousActivity(View view){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
    }

    private void getPreviousActivityValues(){
        Bundle intent = getIntent().getExtras();

        name = intent.getString("name");
        password = intent.getString("password");
        gender = intent.getString("gender");

        StringBuilder builder = new StringBuilder("");

        builder.append("Activity2->").append("name:").append(name).append(" ").append("Password:").append(password).append(" ").append("Gender:").append(gender);
        Log.i(  "Form Data :" , builder.toString() );

        TextView nameTV = findViewById(R.id.nameTV);
        TextView passwordTV = findViewById(R.id.passwordTV);
        TextView genderTV = findViewById(R.id.genderTV);

        nameTV.setText("Name:" + name);
        passwordTV.setText("Password:" + password);
        genderTV.setText("Gender:" + gender);

    }
}
