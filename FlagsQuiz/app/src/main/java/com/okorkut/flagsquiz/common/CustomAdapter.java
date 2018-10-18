package com.okorkut.flagsquiz.common;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.okorkut.flagsquiz.R;
import com.okorkut.flagsquiz.model.Ranking;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Ranking> rankings;

    public CustomAdapter(Context context, List<Ranking> rankings) {
        this.context = context;
        this.rankings = rankings;
    }

    @Override
    public int getCount() {
        return rankings.size();
    }

    @Override
    public Object getItem(int position) {
        return rankings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view =  inflater.inflate(R.layout.row, null);

        ImageView imgTop = view.findViewById(R.id.imgTop);
        TextView txtTop = view.findViewById(R.id.txtTop);

        if (position == 0){ //top1
            imgTop.setImageResource(R.drawable.top1);
        } else if(position == 1){//top2
            imgTop.setImageResource(R.drawable.top2);
        } else {
            imgTop.setImageResource(R.drawable.top3);
        }

        txtTop.setText(String.format("%.1f",rankings.get(position).getScore()));

        return view;
    }
}
