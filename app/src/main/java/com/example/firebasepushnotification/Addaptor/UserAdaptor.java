package com.example.firebasepushnotification.Addaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasepushnotification.Models.UserModel;
import com.example.firebasepushnotification.R;
import com.example.firebasepushnotification.SendNotificaiton;

import java.util.ArrayList;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.MyViewHolder> {

    Context context;
    ArrayList<UserModel> userModels;

    public UserAdaptor(Context context, ArrayList<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvitemCount.setText(String.valueOf(position + 1));
        holder.tvItemKey.setText(userModels.get(position).getKey());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = userModels.get(position).getKey();
                Intent intent = new Intent(context, SendNotificaiton.class);
                intent.putExtra("key", key);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvitemCount, tvItemKey;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitemCount = itemView.findViewById(R.id.itemCount);
            tvItemKey = itemView.findViewById(R.id.userId);
        }
    }
}
