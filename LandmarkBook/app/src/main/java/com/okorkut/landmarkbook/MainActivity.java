package com.okorkut.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.listView);

        final ArrayList<String> landmarkBooks = new ArrayList<String>();

        landmarkBooks.add("Pisa");
        landmarkBooks.add("Colosseum");
        landmarkBooks.add("Eiffel");
        landmarkBooks.add("London Bridge");

        final ArrayList<Bitmap> landmarkImages = new ArrayList<Bitmap>();

        Bitmap pisa = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.pisa);
        Bitmap colesseum = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.colosseum);
        Bitmap eiffel = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.eiffel);
        Bitmap bridge = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.bridge);

        landmarkImages.add(pisa);
        landmarkImages.add(colesseum);
        landmarkImages.add(eiffel);
        landmarkImages.add(bridge);

        /**
         * Listview ve array bağlantısı sağlanacak
         */
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, landmarkBooks);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

                intent.putExtra("name", landmarkBooks.get(position));

                //selectedImage = landmarkImages.get(position);

                Bitmap bitmap = landmarkImages.get(position);

                Globals.getInstanse().setChoosenImage(bitmap);

                startActivity(intent);
            }
        });
    }
}
