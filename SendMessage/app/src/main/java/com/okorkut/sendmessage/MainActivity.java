package com.okorkut.sendmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> models;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillList();
    }


    private void fillList(){
        models = new ArrayList<Model>();

        Model model1 = new Model("model1","5432321111");
        Model model2 = new Model("model2","5432321112");
        Model model3 = new Model("model3","5432321113");
        Model model4 = new Model("model4","5432321114");
        Model model5 = new Model("model5","5432321115");
        Model model6 = new Model("model6","5432321116");
        Model model7 = new Model("model7","5432321117");

        models.add(model1);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        models.add(model5);
        models.add(model6);
        models.add(model7);

        ListView listView = findViewById(R.id.messageList);

        MessageAdapter adapter =  new MessageAdapter(models,this,this);

        listView.setAdapter(adapter);

    }
}
