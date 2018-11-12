package com.okorkut.socialnetworkapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

public class SetupActivity extends AppCompatActivity {

    private EditText userNameET, fullNameET, countryNameET;
    //private Button saveInformationBtn;
    //private CircleImageView profileImg;

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;
    private StorageReference userProfileImageRef;

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

        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        userNameET = findViewById(R.id.setup_username);
        fullNameET = findViewById(R.id.setup_full_name);
        countryNameET = findViewById(R.id.setup_country_name);
        //saveInformationBtn = findViewById(R.id.setup_information_button);

        //profileImg = findViewById(R.id.setup_profile_image);

        loadingBar = findViewById(R.id.setup_progressBar);

    }

    public void saveAccountSetupInformation(View view){

        String username = userNameET.getText().toString();
        String fName = fullNameET.getText().toString();
        String country = countryNameET.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please write your username", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fName)){
            Toast.makeText(this,"Please write your full name", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(country)){
            Toast.makeText(this,"Please write your country", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setVisibility(View.VISIBLE);

            HashMap<String, Object> userMap = new HashMap<String, Object>();

            userMap.put("username", username);
            userMap.put("fullname", fName);
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

    public void profileOnClick(View view) {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null)
        {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setMultiTouchEnabled(true)
                    .start(this);
           /*CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

             CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setMultiTouchEnabled(true)
                    .start(this);*/
        }

      /*  CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);*/


        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(requestCode == 203){
                Uri resultUri = result.getUri();

                loadingBar.setVisibility(View.VISIBLE);

                StorageReference filePath = userProfileImageRef.child(currentUserId + ".jpg");

                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SetupActivity.this, "Profile Image stored successfully to Firebase storage...", Toast.LENGTH_SHORT).show();

                            final String downloadUrl = task.getResult().getUploadSessionUri().toString();

                            usersRef.child("profileimage").setValue(downloadUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if(task.isSuccessful()) {
                                                Intent selfIntent = new Intent(SetupActivity.this, SetupActivity.class);
                                                startActivity(selfIntent);

                                                Toast.makeText(SetupActivity.this, "Profile Image stored to Firebase Database Successfully...", Toast.LENGTH_SHORT).show();
                                                loadingBar.setVisibility(View.GONE);
                                            } else {
                                                String message = task.getException().getMessage();
                                                Toast.makeText(SetupActivity.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                                                loadingBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        } else {
                            Log.d("ErrorAPP" , "isSuccessful false");
                        }
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

                Toast.makeText(this, "Error Occured:" + error  ,Toast.LENGTH_LONG).show();
                loadingBar.setVisibility(View.GONE);
            } else {
                Toast.makeText(this, "Error Occured: Image can be cropped. Try Again. Code: " +requestCode+ " Error:" + result  ,Toast.LENGTH_LONG).show();
                loadingBar.setVisibility(View.GONE);
            }
        }

    }

    private void sendUserToMainActivity() {
        Intent mainIntent = new Intent(SetupActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);

        finish();
    }


}