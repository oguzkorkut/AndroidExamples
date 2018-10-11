package com.okorkut.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Model> models;

    ListView listView;

    AdapterModel  adapterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createList(){
        models = new ArrayList<Model>();

        Model model1 = new Model("name1", "lastname1","5431231211");
        Model model2 = new Model("name2", "lastname2","5431231212");
        Model model3 = new Model("name3", "lastname3","5431231213");
        Model model4 = new Model("name4", "lastname4","5431231214");
        Model model5 = new Model("name5", "lastname5","5431231215");
        Model model6 = new Model("name6", "lastname6","5431231216");
        Model model7 = new Model("name7", "lastname7","5431231217");

        models.add(model1);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        models.add(model5);
        models.add(model6);
        models.add(model7);
    }

    public void go(){
        listView = findViewById(R.id.list_view);

        adapterModel = new AdapterModel(models,this, this);

        listView.setAdapter(adapterModel);
    }
}
