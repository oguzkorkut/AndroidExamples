package com.okorkut.androidui_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CreateMessage> {

    Context context;

    List<MessageModel> messageModels;

    public MessageAdapter(Context context, List<MessageModel> messageModels) {
        this.context = context;
        this.messageModels = messageModels;
    }

    @Override
    public CreateMessage onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout, parent,false);
        return new CreateMessage(view);
    }

    @Override
    public void onBindViewHolder(CreateMessage holder, final int position) {

        holder.message.setText(messageModels.get(position).getMessage());
        holder.name.setText(messageModels.get(position).getName());
        holder.image.setImageResource(messageModels.get(position).getImageId());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, messageModels.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class CreateMessage extends  RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView message;
        public CreateMessage(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);

        }
    }
}
