package com.okorkut.realmgrafikornek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    EditText buyukTansiyonET, kucukTansiyonET;
    Button ekleBtn;

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        buyukTansiyonET = findViewById(R.id.buyukTansiyon);
        kucukTansiyonET = findViewById(R.id.kucukTansiyon);

        ekleBtn = findViewById(R.id.ekle);

        barChart = findViewById(R.id.barChart);
    }

    public void ekle(View view){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                
            }
        });

    }
}
