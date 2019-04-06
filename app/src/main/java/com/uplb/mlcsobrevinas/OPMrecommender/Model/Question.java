package com.uplb.mlcsobrevinas.OPMrecommender.Model;

public class Question {
    private String Question, AnswerA, AnswerB, AnswerC, AnswerD, AnswerE, CategoryId;
    private Integer ItemScore;

    public Question() {
    }

    public Question(String question, String answerA, String answerB, String answerC, String answerD, String answerE, String CategoryId, Integer itemScore) {
        Question = question;
        AnswerA = answerA;
        AnswerB = answerB;
        AnswerC = answerC;
        AnswerD = answerD;
        AnswerE = answerE;
        CategoryId = CategoryId;
        ItemScore = itemScore;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String answerB) {
        AnswerB = answerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String answerC) {
        AnswerC = answerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String answerD) {
        AnswerD = answerD;
    }

    public String getAnswerE() {
        return AnswerE;
    }

    public void setAnswerE(String answerE) {
        AnswerE = answerE;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String CategoryId) {
        CategoryId = CategoryId;
    }

    public Integer getItemScore() {
        return ItemScore;
    }

    public void setItemScore(Integer itemScore) {
        ItemScore = itemScore;
    }
}
