package com.okorkut.mydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("musicians", MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians( name VARCHAR, age INT(2))");

            //myDatabase.execSQL("insert into musicians(name, age) values ('Oğuz', 28)");
            //myDatabase.execSQL("insert into musicians(name, age) values ('James', 50)");
            //myDatabase.execSQL("insert into musicians(name, age) values ('Kirk', 55)");
            //myDatabase.execSQL("insert into musicians(name, age) values ('Rob', 65)");
            //myDatabase.execSQL("insert into musicians(name, age) values ('Lars', 60)");

            //myDatabase.execSQL("delete from musicians where name = 'James'");
            //myDatabase.execSQL("update musicians set age = 49 where name = 'Kirk'");


            /**
             * Verileri toplamada kullanilir
             */
            Cursor cursor = myDatabase.rawQuery("select * from musicians", null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");


            if ( cursor.moveToFirst()){
                do{
                    System.out.println("Name: " + cursor.getString(nameIx));
                    System.out.println("Age: " + cursor.getString(ageIx));
                } while (cursor.moveToNext());
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }
}
