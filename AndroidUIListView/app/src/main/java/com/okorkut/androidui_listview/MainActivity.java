package com.okorkut.androidui_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<UserModel> userModels = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void fillList(){
        userModels = new ArrayList<UserModel>();

        UserModel userModel = new UserModel("Oğuz", 28, "Korkut", "Fenerbahçe");

        UserModel userModel2 = new UserModel("Vakkas", 20, "Korkut", "Fenerbahçe");

        UserModel userModel3 = new UserModel("Hasan", 8, "Bozkaya", "Fenerbahçe");

        UserModel userModel4 = new UserModel("Hamza", 3, "Oktaş", "Fenerbahçe");

        userModels.add(userModel);
        userModels.add(userModel2);
        userModels.add(userModel3);
        userModels.add(userModel4);

    }
}
