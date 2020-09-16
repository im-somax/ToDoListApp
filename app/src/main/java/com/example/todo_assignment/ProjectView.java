package com.example.todo_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ProjectView extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference projectRef = db.collection("Projects");
    TextView projectName, projectDesc, endpage;
    Button btnAddTask;
    private ProjectAdapter projectAdapter;
    FirebaseFirestore reference;
    RecyclerView projectList;
    ArrayList<Tasks> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        projectName = findViewById(R.id.projectName);
        projectDesc = findViewById(R.id.projectDesc);
        endpage = findViewById(R.id.endpage);
        btnAddTask = findViewById(R.id.btnAddTask);
        setUpRecyclerView();
        // import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");

        // customize font
        projectName.setTypeface(MMedium);
        projectDesc.setTypeface(MLight);
        endpage.setTypeface(MLight);
        btnAddTask.setTypeface(MLight);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProjectView.this, NewTaskActivity.class));
            }
        });
    }
    private void setUpRecyclerView() {
        Query query = projectRef.orderBy("priority", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Tasks> options = new FirestoreRecyclerOptions.Builder<Tasks>()
                .setQuery(query, Tasks.class)
                .build();
        projectAdapter = new ProjectAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.projectList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(projectAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
             }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ProjectAdapter.deleteProject(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        projectAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Projects projects = documentSnapshot.toObject(Projects.class);
                String id = documentSnapshot.getId();
                String path = documentSnapshot.getReference().getPath();
                Toast.makeText(ProjectView.this,
                        "Position: " + position + " ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        projectAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        projectAdapter.stopListening();
    }
}