package com.uplb.mlcsobrevinas.OPMrecommender;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Start extends AppCompatActivity {
    Button btnStart;
    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        Fragment selectFragment = null;
  //      selectFragment = CategoryFragment.newInstance();

        setDefaultFragment();


        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, CategoryFragment .newInstance());
        transaction.commit();
    }



}
