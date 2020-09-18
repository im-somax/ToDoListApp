 package com.example.todo_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

 public class NewTask extends AppCompatActivity {

    TextView titlepage,addtitle,adddesc,adddate;
    EditText tasktitle, taskdesc, taskdate;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer taskNum = new Random().nextInt();
    String keydoes = Integer.toString(taskNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        titlepage = findViewById(R.id.titlepage);
        addtitle = findViewById(R.id.addtitle);
        adddesc = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);

        tasktitle = findViewById(R.id.titleDoes);
        taskdesc = findViewById(R.id.descdoes);
        taskdate = findViewById(R.id.datedoes);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");

        // customize font
        titlepage.setTypeface(MMedium);

        addtitle.setTypeface(MLight);
        tasktitle.setTypeface(MMedium);

        adddesc.setTypeface(MLight);
        taskdesc.setTypeface(MMedium);

        adddate.setTypeface(MLight);
        taskdate.setTypeface(MMedium);

        btnSaveTask.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);
    }
     private void saveTask() {
         String title = tasktitle.getText().toString();
         String description = taskdesc.getText().toString();
         String date = taskdate.getText().toString();
         if (title.trim().isEmpty()) {
             Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
             return;
         }
         else if(description.trim().isEmpty())
         {
             Toast.makeText(this, "Please insert a description", Toast.LENGTH_SHORT).show();
             return;
         }
         else if(date.trim().isEmpty())
         {
             Toast.makeText(this, "Please insert a date", Toast.LENGTH_SHORT).show();
             return;
         }
         CollectionReference notebookRef = FirebaseFirestore.getInstance()
                 .collection("Notebook");
         notebookRef.add(new Tasks(title, description, date));
         Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
         finish();
     }
}