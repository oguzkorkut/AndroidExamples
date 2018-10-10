package com.okorkut.androidui_listview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    List<UserModel> userModels = null;
    Context context;

    public UserListAdapter(List<UserModel> userModels, Context context) {
        this.userModels = userModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int i) {
        return userModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View layout = LayoutInflater.from(context).inflate(R.layout.layout, viewGroup, false);

        TextView name = layout.findViewById(R.id.name);
        TextView lastname = layout.findViewById(R.id.lastname);
        TextView age = layout.findViewById(R.id.age);
        TextView team = layout.findViewById(R.id.team);

        name.setText(userModels.get(i).getName());
        lastname.setText(userModels.get(i).getLastname());
        age.setText(Integer.toString(userModels.get(i).getAge())) ;
        team.setText(userModels.get(i).getTeam());

        return layout;
    }
}
