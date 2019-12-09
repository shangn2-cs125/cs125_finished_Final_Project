package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.Locale;

public class Question extends AppCompatActivity {
    private String Q1 = "You are in a running race. You excel the second place. Which place are you in?";
    private String Q2 = "You are in a running race. You excel the last one. Which place are you in?";
    private String Q3 = "Calculate: what is 1000 + 40 + 1000 + 30 + 1000 + 20 + 1000 + 10?";
    private String Q4 = "Mary's parents have five daughters: the first daughter is named nana;" +
            "the second daughter is named nene;" +
            "the third daughter is named nini;" +
            "the fourth daughter is named nono." +
            "What is the name of the fifth daughter?";
    private String Q5 = "On average, how many books can you put in an empty backpack?";
    private String A1 = "After you excel the second place, you just replace him -- now you are in the second place!";
    private String A2 = "Paradox: how can you excel the last one?";
    private String A3 = "Answer: 4100 (calculate it slower, or use a calculator!)";
    private String A4 = "It's Mary! Read the question again!";
    private String A5 = "1";
    private String challenge = "Challenge ";
    private Button submit;
    private TextView title;
    private TextView score;
    private TextView timer;
    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;
    private TextView correction;
    private MediaPlayer mediaPlayer;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent myIntent = getIntent();
        user = myIntent.getStringExtra("Username");
        timeLeftinMillis = myIntent.getLongExtra("setTime", 60000);
        mediaPlayer = MediaPlayer.create(Question.this, R.raw.anger);
        mediaPlayer.start();
        startTimer();
        final String C1 = challenge + "#1";
        final String C2 = challenge + "#2";
        final String C3 = challenge + "#3";
        final String C4 = challenge + "#4";
        final String C5 = challenge + "#5";
        title = findViewById(R.id.title);
        submit = findViewById(R.id.submit);
        score = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        correction = findViewById(R.id.correction);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TextView answer = findViewById(R.id.answer);
                String Title = title.getText().toString();
                String Answer = answer.getText().toString();
                if (Title.equals(C1)) {
                    if (!Answer.equals("2")) {
                        showMyDialog();
                        updateUICorrection();
                    } else {
                        openDialog();
                        updateUI();
                    }
                }
                if (Title.equals(C2)) {
                    if (!Answer.equals("")) {
                        showMyDialog();
                        updateUICorrection();
                    } else {
                        openDialog();
                        updateUI();
                    }
                }
                if (Title.equals(C3)) {
                    if (!Answer.equals("4100")) {
                        showMyDialog();
                        updateUICorrection();
                    } else {
                        openDialog();
                        updateUI();
                    }
                }
                if (Title.equals(C4)) {
                    if (!Answer.equals("Mary")) {
                        showMyDialog();
                        updateUICorrection();
                    } else {
                        openDialog();
                        updateUI();
                    }
                }
                if (Title.equals(C5)) {
                    if (!Answer.equals("1")) {
                        showMyDialog();
                        updateUICorrection();
                    } else {
                        openDialog();
                        updateUI();
                        finish();
                    }
                }
            }
        });
    }

    /** begin counting the time.*/
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftinMillis, 1000) {
            @Override
            public void onTick(long timeTilFinished) {
                timeLeftinMillis = timeTilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                ScoreData myData = new ScoreData(title.getText().toString(), score.getText().toString(),
                        user);
                ScoreData.scoreData.add(myData);
                mediaPlayer.stop();
                sorryYouFailed();
            }
        }.start();
    }

    /** user failed to ans the question.*/
    private void sorryYouFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Question.this);
        builder.setTitle("Better Luck Next Time!").setMessage("You did not pass the test! Your score has been saved in the ranking.").
                setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent my = new Intent(Question.this, MainActivity.class);
                        startActivity(my);
                        finish();
                    }
                });
        builder.create().show();
    }

    /** change the timer context.*/
    private void updateCountDownText() {
        int minutes = (int) (timeLeftinMillis / 1000) / 60;
        int seconds = (int) (timeLeftinMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }

    /** pop.*/
    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Question.this);
        builder.setTitle("Opps!").setMessage("You shall put something much better than that!").
                setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }


    /** Cheers with the user.*/
    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Hello");
    }

    /** Refresh the screen that user is working on.*/
    private void updateUI() {
        TextView question = findViewById(R.id.question);
        TextView title = findViewById(R.id.title);
        String challenge = "Challenge ";
        correction.setVisibility(View.INVISIBLE);
        if (question.getText().toString().equals(Q1)) {
            title.setText(challenge + "#2");
            question.setText((Q2));
            score.setText("20/100");
        } else if (question.getText().toString().equals(Q2)) {
            title.setText((challenge + "#3"));
            question.setText(Q3);
            score.setText("40/100");
        } else if (question.getText().toString().equals(Q3)) {
            title.setText(challenge + "#4");
            question.setText(Q4);
            score.setText("60/100");
        } else if (question.getText().toString().equals(Q4)) {
            title.setText(challenge + "#5");
            question.setText(Q5);
            score.setText("80/100");
        } else if (question.getText().toString().equals(Q5)) {
            score.setText("100/100");
            mediaPlayer.stop();
            return;
        }
        EditText answer = findViewById(R.id.answer);
        answer.setText("");
    }

    private void updateUICorrection() {
        TextView answer = findViewById(R.id.correction);
        TextView question = findViewById(R.id.question);
        if (question.getText().toString().equals(Q1)) {
            correction.setVisibility(View.VISIBLE);
            answer.setText(A1);
        } else if (question.getText().toString().equals(Q2)) {
            correction.setVisibility(View.VISIBLE);
            answer.setText(A2);
        } else if (question.getText().toString().equals(Q3)) {
            correction.setVisibility(View.VISIBLE);
            answer.setText(A3);
        } else if (question.getText().toString().equals(Q4)) {
            correction.setVisibility(View.VISIBLE);
            answer.setText(A4);
        } else if (question.getText().toString().equals(Q5)) {
            correction.setVisibility(View.VISIBLE);
            answer.setText(A5);
        }
    }
}
