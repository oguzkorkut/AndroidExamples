package com.okorkut.landmarkbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");

        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);

        textView.setText(name);

        //imageView.setImageBitmap(MainActivity.selectedImage);

        imageView.setImageBitmap(Globals.getInstanse().getChoosenImage());
    }
}
