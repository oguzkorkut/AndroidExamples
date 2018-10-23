package com.okorkut.buttonui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.bloder.magic.view.MagicButton;

public class MainActivity extends AppCompatActivity {

    MagicButton btnFacebook, btnYoutube, btnTwitter, btnGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFacebook = findViewById(R.id.facebook_magic_button);
        btnYoutube = findViewById(R.id.youtube_magic_button);
        btnTwitter = findViewById(R.id.twitter_magic_button);
        btnGithub = findViewById(R.id.github_magic_button);

        btnFacebook.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Facebook", Toast.LENGTH_SHORT).show();
            }
        });

        btnYoutube.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Youtube", Toast.LENGTH_SHORT).show();
            }
        });

        btnTwitter.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Twitter", Toast.LENGTH_SHORT).show();
            }
        });

        btnGithub.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Github", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
