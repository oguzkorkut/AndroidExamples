package com.okorkut.androidui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button buton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.text);
        buton = (Button)findViewById(R.id.hesaplaBtn);
        textView = (TextView)findViewById(R.id.sonucTV);

        click();

    }

    private void click(){
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deger = editText.getText().toString();
                int sayi = Integer.parseInt(deger);

                System.out.println("Sayi: " + sayi);

                int sonuc = hesapla(sayi);

                System.out.println("Sonuç: " + sonuc);

                textView.setText(Integer.toString(sonuc));
            }
        });
    }


    private int  hesapla(int sayi){

        int sonuc = 1;

        for (int i = 1; i <= sayi; i++){
            sonuc = sonuc * i;
        }

        return sonuc;
    }


}
