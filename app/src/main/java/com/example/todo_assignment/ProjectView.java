package com.example.todo_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ProjectView extends AppCompatActivity {

    TextView projectName, projectDesc, endpage;
    Button btnAddTask;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");
    private DoesAdapter doesAdapter;
    FirebaseFirestore reference;
    RecyclerView projectList;
    ArrayList<MyDoes> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_view);

        projectName = findViewById(R.id.projectName);
        projectDesc = findViewById(R.id.projectDesc);
        endpage = findViewById(R.id.endpage);

        btnAddTask = findViewById(R.id.btnAddTask);

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
}