package com.okorkut.androidui_listview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<MessageModel> messageModels;

    Context context;

    public MessageAdapter(List<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return messageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return messageModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout, parent, false );

        ImageView imageId = view.findViewById(R.id.imageId);
        TextView person = view.findViewById(R.id.person);
        TextView content = view.findViewById(R.id.content);

        imageId.setImageResource(messageModels.get(position).getImageId());
        person.setText(messageModels.get(position).getPerson());
        content.setText(messageModels.get(position).getContent());

        return view;
    }
}
