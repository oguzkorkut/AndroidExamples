package com.okorkut.activitylifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterModel extends BaseAdapter {

    List<Model> models;
    Context context;
    Activity activity;

    public AdapterModel(List<Model> models, Context context, Activity activity) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);

        TextView nameTV = convertView.findViewById(R.id.name);
        TextView lastnameTV = convertView.findViewById(R.id.lastname);
        TextView phoneNoTV = convertView.findViewById(R.id.phoneNo);

        LinearLayout linearLayout = convertView.findViewById(R.id.mainLayout);

        final String name = models.get(position).getName();
        final String lastname = models.get(position).getLastname();
        final String phoneNo = models.get(position).getPhoneNo();

        nameTV.setText(name);
        lastnameTV.setText(lastname);
        phoneNoTV.setText(phoneNo);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);

                intent.putExtra("name", name);
                intent.putExtra("lastname", lastname);
                intent.putExtra("phoneNo", phoneNo);

                activity.startActivity(intent);

            }
        });

        return convertView;
    }
}
