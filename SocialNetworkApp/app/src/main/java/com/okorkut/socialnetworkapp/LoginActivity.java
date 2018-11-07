package com.okorkut.socialnetworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText userEmailET, userPasswordET;
    private TextView needNewAccountLink;

    private ProgressBar loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        needNewAccountLink = findViewById(R.id.register_account_link);
        userEmailET = findViewById(R.id.login_email);
        userPasswordET = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_button);

        loadingBar =  findViewById(R.id.login_progressBar);

        needNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currrentUser = mAuth.getCurrentUser();

        if(currrentUser != null){
            sendUserToMainActivity();
        }
    }

    public void allowingUserToLogin(View view) {

        String email = userEmailET.getText().toString();
        String password = userPasswordET.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please write your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please write your password", Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "You are Logged In successfully", Toast.LENGTH_SHORT).show();
                        loadingBar.setVisibility(View.GONE);

                        sendUserToMainActivity();
                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(LoginActivity.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                        loadingBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private void sendUserToMainActivity() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);

        finish();
    }

    private void sendUserToRegisterActivity() {

        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
        //finish();
    }
}
