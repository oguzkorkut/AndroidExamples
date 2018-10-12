package com.okorkut.sendmail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText eMailET;
    EditText subjectET;
    EditText contentET;

    Button sendBtn;

    String email, subject, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eMailET = findViewById(R.id.mailaddress);
        subjectET = findViewById(R.id.mailSubject);
        contentET = findViewById(R.id.mailContent);

        sendBtn = findViewById(R.id.sendMail);

    }

    public void send(View view){
        email = eMailET.getText().toString();
        subject = subjectET.getText().toString();
        content = contentET.getText().toString();

        try {
            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("message/rfc822");

            intent.putExtra(Intent.EXTRA_EMAIL, email);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT,content);


            startActivity(Intent.createChooser(intent, "Send Mail"));

        } catch (Exception e){
            Log.i("Mail Exception", e.getMessage());
        }
    }
}
