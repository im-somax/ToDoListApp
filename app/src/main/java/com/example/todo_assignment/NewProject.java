package com.example.todo_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class NewProject extends AppCompatActivity {

    TextView titlepage,addtitle,adddesc;
    EditText projectTitle, projectDesc;
    Button btnSaveProject, btnCancel;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        titlepage = findViewById(R.id.titlepage);
        addtitle = findViewById(R.id.addtitle);
        adddesc = findViewById(R.id.adddesc);

        projectTitle = findViewById(R.id.projectTitle);
        projectDesc = findViewById(R.id.projectDesc);
        btnSaveProject = findViewById(R.id.btnSaveProject);
        btnCancel = findViewById(R.id.btnCancel);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        btnSaveProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProject();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");

        // customize font
        titlepage.setTypeface(MMedium);

        addtitle.setTypeface(MLight);
        projectTitle.setTypeface(MMedium);

        adddesc.setTypeface(MLight);
        projectDesc.setTypeface(MMedium);

        btnSaveProject.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);
    }
    private void saveProject() {
        String title = projectTitle.getText().toString();
        String description = projectDesc.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(description.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert a description", Toast.LENGTH_SHORT).show();
            return;
        }
        CollectionReference projectRef = FirebaseFirestore.getInstance()
                .collection("Projects");
        projectRef.add(new Projects(title, description,priority));
        Toast.makeText(this, "Project added", Toast.LENGTH_SHORT).show();
        finish();
    }
}