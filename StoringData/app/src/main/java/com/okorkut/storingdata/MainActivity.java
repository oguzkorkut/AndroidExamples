package com.okorkut.storingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Hangi activity icerisinde kullanilacagi seciliyor
         * MODE_PRIVATE: kendi uygulama icerisinde  kullanilabilir.
         *
         */
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.okorkut.storingdata", Context.MODE_PRIVATE);

        int age = 40;

        sharedPreferences.edit().putInt("userAge",age).apply();

        int savedAge = sharedPreferences.getInt("userAge",0);

        System.out.println("Saved Age:" + savedAge);

        TextView textView = findViewById(R.id.textView);

        textView.setText(Integer.toString(savedAge));

    }
}
