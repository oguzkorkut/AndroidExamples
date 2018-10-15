package com.okorkut.realminsert;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

    UserAdapter userAdapter;

    ListView listView;

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

       listView = findViewById(R.id.listView);

       //listViewItemPosition();
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
                //Log.i("User",user.toString());


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();

                nameET.setText("");
                usernameET.setText("");
                passwordET.setText("");

                listViewItemPosition();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e("Error", error.toString());
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });

        showUsers();
    }

    public void showUsers(){
        realm.beginTransaction();;

        RealmResults<User> results = realm.where(User.class).findAll();

        if (results != null && results.size() >0){
            StringBuilder usersStr= new StringBuilder("");
            for (User user: results){
                usersStr.append(user.toString()).append("\n");

            }

            userAdapter = new UserAdapter(results, getApplicationContext());

            listView.setAdapter(userAdapter);

            Log.i("Users",usersStr.toString());
        }


        realm.commitTransaction();


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

    public void listViewItemPosition(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Item Position", Integer.toString(position));
                //delete(position);
                open(position);
            }
        });
    }

    public void delete(final int position){
        final RealmResults<User> users = realm.where(User.class).findAll();

        Log.i("User List Size:", "" + users.size());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = users.get(position);
                user.deleteFromRealm();
            }
        });
    }


    public void open(final int position){
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.alertlayout, null);

        Button yesBtn = view.findViewById(R.id.yes);
        Button noBtn = view.findViewById(R.id.no);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(view);
        alert.setCancelable(false);//boş tıklamada kapanmaz

        final AlertDialog dialog = alert.create();

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
