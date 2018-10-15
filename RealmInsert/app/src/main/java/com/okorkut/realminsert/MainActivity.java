package com.okorkut.realminsert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;

    EditText nameET, usernameET, passwordET;
    RadioGroup genderRG;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       generateRealm();

       insertPerson("Mehmet", "Korkut", 1, 56);
       insertPerson("Oğuz", "Korkut", 1, 28);
       insertPerson("Vakkas", "Korkut", 2, 20);

       showPerson();

       nameET =findViewById(R.id.name);
       usernameET = findViewById(R.id.username);
       passwordET = findViewById(R.id.password);
       genderRG = findViewById(R.id.genderRG);

       signUp = findViewById(R.id.signUp);
    }

    public void generateRealm(){
        realm = Realm.getDefaultInstance();
    }

    public void signUp(View view){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);

                user.setName(nameET.getText().toString());
                user.setUsername(usernameET.getText().toString());
                Integer id= genderRG.getCheckedRadioButtonId();
                RadioButton genderRB = findViewById(id);
                user.setGender(genderRB.getText().toString());
                user.setPassword(passwordET.getText().toString());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void insertPerson(String name, String lastname, int salary, int age){
        realm.beginTransaction();

        Person person = realm.createObject(Person.class);

        person.setName(name);
        person.setLastname(lastname);
        person.setSalary(salary);
        person.setAge(age);

        realm.commitTransaction();
    }

    public void showPerson(){
        realm.beginTransaction();

        RealmResults<Person> results = realm.where(Person.class).findAll();

        for (Person person : results){
            Log.i("Person Result",  person.toString());
        }

        realm.commitTransaction();
    }
}
