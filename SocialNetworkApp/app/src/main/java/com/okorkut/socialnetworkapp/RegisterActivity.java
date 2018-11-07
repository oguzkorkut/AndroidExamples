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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmailET, userPasswordET, userConfirmPasswordET;
    private Button createAccountBtn;

    private ProgressBar loadingBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        userEmailET = findViewById(R.id.register_email);
        userPasswordET = findViewById(R.id.register_password);
        userConfirmPasswordET = findViewById(R.id.register_confirm_password);

        createAccountBtn = findViewById(R.id.register_create_account);

        loadingBar =  findViewById(R.id.progressBar);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

    }

    private void createNewAccount() {

        String email = userEmailET.getText().toString();
        String password= userPasswordET.getText().toString();
        String confirmPassword = userConfirmPasswordET.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
        } else if(!password.equalsIgnoreCase(confirmPassword)){
            Toast.makeText(this, "Your password do not match with your confirm password", Toast.LENGTH_SHORT).show();
        } else {

           /* loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, while we are creating your new account...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true); */

            loadingBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        sendUserToSetupActivity();

                        Toast.makeText(RegisterActivity.this, "You are authenticated successfully",Toast.LENGTH_SHORT).show();
                        //loadingBar.dismiss();
                        loadingBar.setVisibility(View.GONE);
                    } else {
                        String message = task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this, "Error Occured:" + message ,Toast.LENGTH_SHORT).show();
                        //loadingBar.dismiss();
                        loadingBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private void sendUserToSetupActivity() {

        Intent setupIntent = new Intent(RegisterActivity.this,SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(setupIntent);

        finish();
    }
}
