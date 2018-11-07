package com.okorkut.socialnetworkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmailET, userPasswordET, userConfirmPasswordET;
    private Button createAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmailET = findViewById(R.id.register_email);
        userPasswordET = findViewById(R.id.register_password);
        userConfirmPasswordET = findViewById(R.id.register_confirm_password);

        createAccountBtn = findViewById(R.id.register_create_account);

    }
}
