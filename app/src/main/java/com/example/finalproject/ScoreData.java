package com.example.finalproject;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreData {
    public static List<ScoreData> scoreData = new ArrayList<>();
    private String score;
    private String questionDied;
    private String userName;
    /** the class constructor.*/
    public ScoreData(String questionDied, String score, String userName) {
        this.questionDied = questionDied;
        this.score = score;
        this.userName = userName;
    }

    /** getter and setter.*/
    public List<ScoreData> getScoreData() {
        return scoreData;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getQuestionDied() {
        return questionDied;
    }
    public void setQuestionDied(String questionDied) {
        this.questionDied = questionDied;
    }
}
