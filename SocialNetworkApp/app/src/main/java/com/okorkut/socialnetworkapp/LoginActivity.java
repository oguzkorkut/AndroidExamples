package com.okorkut.socialnetworkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText userEmailBtn, userPasswordBtn;
    private TextView needNewAccountLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        needNewAccountLink = findViewById(R.id.register_account_link);
        userEmailBtn = findViewById(R.id.login_email);
        userPasswordBtn = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);

        needNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });
    }

    private void sendUserToRegisterActivity() {

        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
        //finish();
    }
}
