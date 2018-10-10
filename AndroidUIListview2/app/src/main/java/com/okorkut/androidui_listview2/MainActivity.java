package com.okorkut.androidui_listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MessageAdapter  messageAdapter;

    List<MessageModel> messageModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        definition();

        fillList();
    }

    private void definition(){
        listView = findViewById(R.id.listview);
    }

    private void fillList(){
        messageModels = new ArrayList<>();

        MessageModel messageModel = new MessageModel("Hello I am Niloya","Niloya",R.drawable.niloya);
        MessageModel messageModel2 = new MessageModel("Hello I am Murat","Murat",R.drawable.murat);
        MessageModel messageModel3 = new MessageModel("Hello I am Dede","Dede",R.drawable.dede);
        MessageModel messageModel4 = new MessageModel("Hello I am Baba","Baba",R.drawable.baba);
        MessageModel messageModel5 = new MessageModel("Hello I am Anne","Anne",R.drawable.anne);
        MessageModel messageModel6 = new MessageModel("Hello I am Babaanne","Babaanne",R.drawable.babaanne);

        messageModels.add(messageModel);
        messageModels.add(messageModel2);
        messageModels.add(messageModel3);
        messageModels.add(messageModel4);
        messageModels.add(messageModel5);
        messageModels.add(messageModel6);

        messageAdapter = new MessageAdapter(messageModels, this);

        listView.setAdapter(messageAdapter);

    }
}
