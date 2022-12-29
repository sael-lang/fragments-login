package com.example.fexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    private EditText ETName, ETId, ETFeedback;
    private Button sendData;
    DatabaseReference databaseReference;
    Feedback feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ETId = findViewById(R.id.id);
        ETName = findViewById(R.id.name);
        ETFeedback = findViewById(R.id.feedback);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("feedback");
        feedback = new Feedback();

        sendData = findViewById(R.id.BtnSendData);

        ETId = findViewById(R.id.id);
        ETName = findViewById(R.id.name);
        ETFeedback = findViewById(R.id.feedback);

        sendData = findViewById(R.id.BtnSendData);

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = ETId.getText().toString();
                String Name = ETName.getText().toString();
                String Feedback = ETFeedback.getText().toString();

                if (TextUtils.isEmpty(Id) && TextUtils.isEmpty(Name) && TextUtils.isEmpty(Feedback)) {
                    Toast.makeText(MainActivity2.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    addDatatoFirebase(Id, Name, Feedback); } }
        });
    }
    private void addDatatoFirebase(String Id, String Name, String Feedback) {
        feedback.setId(Id);
        feedback.setName(Name);
        feedback.setFeedback(Feedback);

        databaseReference.push().setValue(feedback);
        Toast.makeText(this, "Data added", Toast.LENGTH_SHORT).show();
    }
    }
