package com.uplb.mlcsobrevinas.OPMrecommender;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uplb.mlcsobrevinas.OPMrecommender.Common.Common;
import com.uplb.mlcsobrevinas.OPMrecommender.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartButton extends AppCompatActivity {

    Button btnPlay;

    FirebaseDatabase database;
    DatabaseReference questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_button);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");

        loadQuestion(Common.CategoryId);

        btnPlay = (Button)findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartButton.this, personalityTest.class);
                startActivity(intent);
            }
        });

    }

    private void loadQuestion(String CategoryId) {
        questions.orderByChild("CategoryId").equalTo(CategoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            Question ques = postSnapshot.getValue(Question.class);
                            Common.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
