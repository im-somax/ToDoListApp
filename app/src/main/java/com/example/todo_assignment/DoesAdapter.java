package com.example.todo_assignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import java.util.ArrayList;

public class DoesAdapter extends FirestoreRecyclerAdapter<MyDoes, DoesAdapter.MyViewHolder>{

    Context context;
    ArrayList<MyDoes> myDoes;

    public DoesAdapter(@NonNull FirestoreRecyclerAdapter<MyDoes> options){
        super(options);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titledoes, descdoes, datedoes, keydoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = itemView.findViewById(R.id.titledoes);
            descdoes = itemView.findViewById(R.id.descdoes);
            datedoes = itemView.findViewById(R.id.datedoes);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does,viewGroup,false)) ;
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull MyDoes model) {
        holder.titledoes.setText(myDoes.get(position).getTitledoes());
        holder.descdoes.setText(myDoes.get(position).getDescdoes());
        holder.datedoes.setText(myDoes.get(position).getDatadoes());

        final String getTitleDoes = myDoes.get(position).getTitledoes();
        final String getDescDoes = myDoes.get(position).getDescdoes();
        final String getDateDoes = myDoes.get(position).getDatadoes();
        final String getKeyDoes = myDoes.get(position).getKeydoes();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aa = new Intent(context, EditTaskDesk.class);
                aa.putExtra("titleDoes",getTitleDoes);
                aa.putExtra("descDoes",getDescDoes);
                aa.putExtra("dateDoes",getDateDoes);
                aa.putExtra("keyDoes",getKeyDoes);
                context.startActivity(aa);
            }
        });
    }
}
