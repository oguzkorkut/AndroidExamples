package com.okorkut.realminsert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       generateRealm();

       insert();
    }

    public void generateRealm(){
        realm = Realm.getDefaultInstance();
    }

    public void insert(){
        realm.beginTransaction();

        Person person = realm.createObject(Person.class);

        person.setName("Oğuz");
        person.setLastname("Korkut");
        person.setSalary(1);
        person.setAge(28);

        realm.commitTransaction();
    }

    public void show(){
        realm.beginTransaction();

        RealmResults<Person> results = realm.where(Person.class).findAll();

        for (Person person : results){
            Log.i("Person Result",  person.toString());
        }

        realm.commitTransaction();
    }
}
