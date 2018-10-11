package com.okorkut.androidui_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.createMessage> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class createMessage extends  RecyclerView.ViewHolder{

        public createMessage(View itemView) {
            super(itemView);
        }
    }
}
