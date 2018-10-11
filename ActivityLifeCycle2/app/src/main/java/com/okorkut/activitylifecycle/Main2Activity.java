package com.okorkut.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView nameTV;
    TextView lastnameTV;
    TextView phoneNoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameTV = findViewById(R.id.name);
        lastnameTV = findViewById(R.id.lastname);
        phoneNoTV = findViewById(R.id.phoneNo);

        getData();
    }

    private void getData(){
        Bundle intent = getIntent().getExtras();

        String name = intent.getString("name");
        String lastname =  intent.getString("lastname");
        String phoneNo = intent.getString("phoneNo");

        nameTV.setText(name);
        lastnameTV.setText(lastname);
        phoneNoTV.setText(phoneNo);
    }
}
