package com.okorkut.realminsert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    List<User> users;

    Context  context;

    public UserAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);

        TextView nameTV= convertView.findViewById(R.id.name);
        TextView usernameTV = convertView.findViewById(R.id.username);
        TextView passwordTV = convertView.findViewById(R.id.password);
        TextView genderTV = convertView.findViewById(R.id.gender);

        nameTV.setText(users.get(position).getName());
        usernameTV.setText(users.get(position).getUsername());
        passwordTV.setText(users.get(position).getPassword());
        genderTV.setText(users.get(position).getGender());

        return convertView;
    }
}
