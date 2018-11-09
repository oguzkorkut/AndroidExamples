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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText userNameET, fullNameET, countryNameET;
    private Button saveInformationBtn;
    private CircleImageView profileImg;

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    String currentUserId;

    final static int Gallery_Pick = 1;

    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mAuth = FirebaseAuth.getInstance();

        currentUserId = mAuth.getCurrentUser().getUid();

        usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

        userNameET = findViewById(R.id.setup_username);
        fullNameET = findViewById(R.id.setup_full_name);
        countryNameET = findViewById(R.id.setup_country_name);
        saveInformationBtn = findViewById(R.id.setup_information_button);

        profileImg = findViewById(R.id.setup_profile_image);

        loadingBar = findViewById(R.id.setup_progressBar);

    }

    public void saveAccountSetupInformation(View view){

        String username = userNameET.getText().toString();
        String fullname = fullNameET.getText().toString();
        String country = countryNameET.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please write your username", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fullname)){
            Toast.makeText(this,"Please write your full name", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(country)){
            Toast.makeText(this,"Please write your country", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setVisibility(View.VISIBLE);

            HashMap userMap = new HashMap();

            userMap.put("username", username);
            userMap.put("fullname", fullname);
            userMap.put("country", country);
            userMap.put("status", "Hey there, i am using Poster Social Network, developers by 32");
            userMap.put("gender", "none");
            userMap.put("dob", "none");
            userMap.put("relationshipstatus", "none");

            usersRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SetupActivity.this, "Your Account is created successfully", Toast.LENGTH_SHORT).show();

                        loadingBar.setVisibility(View.GONE);

                        sendUserToMainActivity();
                    } else {
                        loadingBar.setVisibility(View.GONE);

                        String message = task.getException().getMessage();

                        Toast.makeText(SetupActivity.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToMainActivity() {
        Intent mainIntent = new Intent(SetupActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);

        finish();
    }

    public void profileOnClick(View view) {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);


    }
}
