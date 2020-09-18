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
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;


public class ProjectAdapter extends FirestoreRecyclerAdapter<Projects,ProjectAdapter.ProjectHolder>{
    private ProjectAdapter.OnItemClickListener listener;

    public ProjectAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }


    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_does,parent,false);
        return new ProjectAdapter.ProjectHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProjectHolder holder, int position, @NonNull Projects model) {
        holder.textViewProjectTitle.setText(model.getTitle());
        holder.textViewProjectDescription.setText(model.getDescription());
    }
    public void deleteProject(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }


    class ProjectHolder extends RecyclerView.ViewHolder{
        TextView textViewProjectTitle;
        TextView textViewProjectDescription;
        TextView textViewPriority;
        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewProjectTitle = itemView.findViewById(R.id.title);
            textViewProjectDescription = itemView.findViewById(R.id.desc);
            textViewPriority = itemView.findViewById(R.id.date);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(ProjectAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
