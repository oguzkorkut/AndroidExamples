package com.okorkut.socialnetworkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText userNameET, fullNameET, countryNameET;
    private Button saveInformationBtn;
    private CircleImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        userNameET = findViewById(R.id.setup_username);
        fullNameET = findViewById(R.id.setup_full_name);
        countryNameET = findViewById(R.id.setup_country_name);
        saveInformationBtn = findViewById(R.id.setup_information_button);

        profileImg = findViewById(R.id.setup_profile_image);




    }
}
