package com.okorkut.alertdialog;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button openDialogBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDialogBtn = findViewById(R.id.openDialog);

    }

    public void open(View v1){
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.alertlayout, null);

        final EditText mailET = view.findViewById(R.id.mailAddress);
        final EditText nameET = view.findViewById(R.id.name);
        final EditText passwordET = view.findViewById(R.id.password);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();

        dialog.show();

        Button signUpBtn = view.findViewById(R.id.signUp);

        Button exitBtn = view.findViewById(R.id.exitDialog);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Open Dialog" ,"Open Dialog");
                Toast.makeText(getApplicationContext(), mailET.getText().toString() + " " + nameET.getText().toString() + " " + passwordET.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }
}
