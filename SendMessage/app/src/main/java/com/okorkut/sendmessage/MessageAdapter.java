package com.okorkut.sendmessage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<Model> models;
    Context context;
    Activity activity;

    public MessageAdapter(List<Model> models, Context context, Activity activity) {
        this.models = models;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView  = LayoutInflater.from(context).inflate(R.layout.messagelayout, parent, false);

        final EditText messageContentET = convertView.findViewById(R.id.messageContent);
        TextView nameTV = convertView.findViewById(R.id.name);
        TextView phoneNoTV = convertView.findViewById(R.id.phoneNo);

        Button sendBtn = convertView.findViewById(R.id.sendBtn);

        final String phoneNo = models.get(position).getPhoneNo();
        phoneNoTV.setText(phoneNo);
        nameTV.setText(models.get(position).getName());

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = messageContentET.getText().toString();
                //tel için tel:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNo));

                intent.putExtra("sms_body", messageContent);

                activity.startActivity(intent);
            }
        });
        return convertView;
    }
}
