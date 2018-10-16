package com.okorkut.realmgrafikornek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

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

        goster();

        //sil();
    }

    public void ekle(View view){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Tansiyon tansiyon = realm.createObject(Tansiyon.class);

                tansiyon.setBuyukTansiyon(buyukTansiyonET.getText().toString());
                tansiyon.setKucukTansiyon(kucukTansiyonET.getText().toString());
            }
        });

        listele();

        goster();
    }

    public void listele(){
        RealmResults<Tansiyon> tansiyons = realm.where(Tansiyon.class).findAll();

        StringBuilder builder = new StringBuilder("");
        for (Tansiyon tansiyon: tansiyons){
            builder.append(tansiyon.toString()).append("\n");
        }

        Log.i("Tansiyon Listesi", builder.toString());
    }

    public void goster(){
        RealmResults<Tansiyon> tansiyons = realm.where(Tansiyon.class).findAll();

        Float buyukTansiyon = 0f;
        Float kucukTansiyon = 0f;

        ArrayList<BarEntry> arrayList = new ArrayList<BarEntry>();

        for (int i=0; i < tansiyons.size(); i++){
            buyukTansiyon = buyukTansiyon + Float.parseFloat(tansiyons.get(i).getBuyukTansiyon());
            kucukTansiyon = kucukTansiyon + Float.parseFloat(tansiyons.get(i).getKucukTansiyon());

        }

        arrayList.add(new BarEntry(buyukTansiyon, 0));
        arrayList.add(new BarEntry(kucukTansiyon, 1));

        BarDataSet barDataSet = new BarDataSet(arrayList, "Toplam Değer");

        ArrayList<String> sutunIsmi = new ArrayList<String>();

        sutunIsmi.add("Büyük Tansiyon");
        sutunIsmi.add("Küçük Tansiyon");

        BarData barData = new BarData(sutunIsmi, barDataSet);

        barChart.setData(barData);
        barChart.setDescription("Tansiyon Değerlerini Göster Grafik Arayüzü..");

        barChart.invalidate();
    }

    public void sil(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //RealmQuery<Tansiyon> tansiyon = realm.where(Tansiyon.class).equalTo("buyukTansiyon", "13");
                RealmResults<Tansiyon> tansiyons = realm.where(Tansiyon.class).findAll();
                tansiyons.deleteFromRealm(1);
            }
        });
    }
}
