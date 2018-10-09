package com.okorkut.artbook;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //static Bitmap chosenImage;

    static ArrayList<String> artNames;
    static ArrayList<Bitmap> artImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        artNames = new ArrayList<String>();
        artImages = new ArrayList<Bitmap>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, artNames);
        listView.setAdapter(arrayAdapter);

        try {
            Main2Activity.database = this.openOrCreateDatabase("Arts", MODE_PRIVATE,null);
            Main2Activity.database.execSQL("create table if not exists arts(name VARCHAR, image BLOB)");

            Cursor cursor = Main2Activity.database.rawQuery("select * from arts", null);

            int nameIx = cursor.getColumnIndex("name");
            int imageIx = cursor.getColumnIndex("image");

            cursor.moveToFirst();

            /*  while(cursor != null){

                System.out.println("MoveToNext:" +  cursor.moveToNext());
              String tempArtName = cursor.getString(nameIx);


                System.out.println("Art Name:" + tempArtName);
                artNames.add(tempArtName);

                byte[] bytes = cursor.getBlob(imageIx);

                Bitmap image = BitmapFactory.decodeByteArray(bytes, 0 , bytes.length);

                artImages.add(image);

                cursor.moveToNext();

                cursor.cursor.moveToNext();


                 // Data değiştirilince array adapter güncellenmesi için tetikleme yapıyor

                arrayAdapter.notifyDataSetChanged();
            }*/
            if (cursor.moveToFirst()){
                do {
                    String tempArtName = cursor.getString(nameIx);
                    System.out.println("Art Name:" + tempArtName);
                    artNames.add(tempArtName);

                    byte[] bytes = cursor.getBlob(imageIx);

                    Bitmap image = BitmapFactory.decodeByteArray(bytes, 0 , bytes.length);

                    artImages.add(image);

                     //Data değiştirilince array adapter güncellenmesi için tetikleme yapıyor

                    arrayAdapter.notifyDataSetChanged();
                } while (!cursor.moveToNext());
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                intent.putExtra("info", "old");
                intent.putExtra("name",  artNames.get(position));
                intent.putExtra("position", position);

                //chosenImage = artImages.get(position);

                startActivity(intent);
            }
        });
    }


    /**
     * menu işlemleri
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /**
         * Menü kullanımı için gerekli obje
         */
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.add_art, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Menu seçilirse ne yapılacağı
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_art){
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);

            intent.putExtra("info","new");

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
