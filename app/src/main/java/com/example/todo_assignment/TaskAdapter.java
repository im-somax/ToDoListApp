package com.example.todo_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class TaskAdapter extends FirestoreRecyclerAdapter<Tasks,TaskAdapter.TaskHolder>{
    private OnItemClickListener listener;

    public TaskAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_does,parent,false);
        return new TaskHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskHolder holder, int position, @NonNull Tasks model) {
        holder.textViewTaskTitle.setText(model.getTitle());
        holder.textViewTaskDescription.setText(model.getDescription());
        holder.textViewTaskDate.setText(model.getDate());
    }

    public void deleteTask(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    class TaskHolder extends RecyclerView.ViewHolder{
        TextView textViewTaskTitle;
        TextView textViewTaskDescription;
        TextView textViewTaskDate;
        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskTitle = itemView.findViewById(R.id.title);
            textViewTaskDescription = itemView.findViewById(R.id.desc);
            textViewTaskDate = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null ){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
