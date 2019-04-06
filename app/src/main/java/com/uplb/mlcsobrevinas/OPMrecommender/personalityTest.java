package com.uplb.mlcsobrevinas.OPMrecommender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uplb.mlcsobrevinas.OPMrecommender.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personalityTest extends AppCompatActivity implements View.OnClickListener {

    Integer item[];

    int index = 0, totalQuestion, score;

    Button btnA, btnB, btnC, btnD, btnE;
    TextView question_text;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference table_user = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_test);


        question_text = (TextView)findViewById(R.id.question_text);
        question_text.setText(Common.questionList.get(index).getQuestion());
        btnA = (Button)findViewById(R.id.btnAnswerA);
        btnB = (Button)findViewById(R.id.btnAnswerB);
        btnC = (Button)findViewById(R.id.btnAnswerC);
        btnD = (Button)findViewById(R.id.btnAnswerD);
        btnE = (Button)findViewById(R.id.btnAnswerE);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (index < totalQuestion) {
            Button clickedButton = (Button) view;
            if (clickedButton == btnA) {
                Common.questionList.get(index).setItemScore(1);
                score = Common.questionList.get(index).getItemScore();
                item[index] = score;
                showQuestion(++index);
            } else if (clickedButton == btnB) {
                Common.questionList.get(index).setItemScore(2);
                score = Common.questionList.get(index).getItemScore();
                item[index] = score;
                showQuestion(++index);
            } else if (clickedButton == btnC) {
                Common.questionList.get(index).setItemScore(3);
                score = Common.questionList.get(index).getItemScore();
                item[index] = score;
                showQuestion(++index);
            } else if (clickedButton == btnD) {
                Common.questionList.get(index).setItemScore(4);
                score = Common.questionList.get(index).getItemScore();
                item[index] = score;
                showQuestion(++index);
            } else if (clickedButton == btnE) {
                Common.questionList.get(index).setItemScore(5);
                score = Common.questionList.get(index).getItemScore();
                item[index] = score;
                showQuestion(++index);
            }
        }
    }

    private void showQuestion(int index){

        if(index < totalQuestion){

            question_text.setText(Common.questionList.get(index).getQuestion());
            question_text.setVisibility(View.VISIBLE);
            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());
            btnE.setText(Common.questionList.get(index).getAnswerE());

        }else{
            computePersonality();
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        totalQuestion = Common.questionList.size();
        item = new Integer[totalQuestion];
    }

    protected void computePersonality(){
        Common.currentUser.setExtro(20 + item[0] - item[5] + item[10] - item[15] + item[20] - item[25] + item[30] - item[35] + item[40] - item[45]);
        Common.currentUser.setAgre(14 - item[1] + item[6] - item[11] + item[16] - item[21] + item[26] - item[31] + item[36] + item[41] + item[46]);
        Common.currentUser.setCons(14 + item[2] - item[7] + item[12] - item[17] + item[22] - item[27] + item[32] - item[37] + item[42] + item[47]);
        Common.currentUser.setNeuro(38 - item[3] + item[8] - item[13] + item[18] - item[23] - item[28] - item[33] - item[38] - item[43] - item[48]);
        Common.currentUser.setOpenn(8 + item[4] - item[9] + item[14] - item[19] + item[24] - item[29] + item[34] + item[39] + item[44] + item[49]);
        table_user.child("User").child(Common.currentUser.getPhone()).child("Extro").setValue(Common.currentUser.getExtro());
        table_user.child("User").child(Common.currentUser.getPhone()).child("Agre").setValue(Common.currentUser.getAgre());
        table_user.child("User").child(Common.currentUser.getPhone()).child("Cons").setValue(Common.currentUser.getCons());
        table_user.child("User").child(Common.currentUser.getPhone()).child("Neuro").setValue(Common.currentUser.getNeuro());
        table_user.child("User").child(Common.currentUser.getPhone()).child("Openn").setValue(Common.currentUser.getOpenn());
    }
}



