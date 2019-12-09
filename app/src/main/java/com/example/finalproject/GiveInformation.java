package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class GiveInformation extends AppCompatActivity {

    private RadioGroup level;
    private RadioButton checked;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveinformation);
        Intent intent = getIntent();
        final TextView userName = findViewById(R.id.helloUser);
        user = intent.getStringExtra("Username");
        level = findViewById(R.id.level);
        int randomNumber = getRandomNumber(30, 60000);
        String playerNumber = Integer.toString(randomNumber);
        if (!user.equals("")) {
            userName.setText(user);
        } else {
            userName.setText("Player#" + playerNumber);
        }
        TextView welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome to our IQ test!");
        TextView ready = findViewById(R.id.ready);
        ready.setText("Are you ready?");
        Button yes = findViewById(R.id.yes);
        Button no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            // the user does not want play -> go back to main menu
            public void onClick(View view) {
                Intent previous = new Intent(GiveInformation.this, MainActivity.class);
                startActivity(previous);
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            // the user chooses to play -> go to question1
            public void onClick(View view) {
                int radioId = level.getCheckedRadioButtonId();
                if (radioId == -1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GiveInformation.this);
                    builder.setTitle("Opps!").setMessage("You shall at least pick a level!").
                            setPositiveButton("Back", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    builder.create().show();
                } else {
                    checked = findViewById(radioId);
                    Intent question1 = new Intent(GiveInformation.this, Question.class);
                    question1.putExtra("level", checked.getText().toString());
                    if (checked.getText().toString().equals("easy")) {
                        long setTime = 60000;
                        question1.putExtra("setTime", setTime);
                        question1.putExtra("Username", userName.getText().toString());
                    } else if (checked.getText().toString().equals("medium")) {
                        long setTime = 30000;
                        question1.putExtra("setTime", setTime);
                        question1.putExtra("Username", userName.getText().toString());
                    } else if (checked.getText().toString().equals("hard")) {
                        long setTime = 15000;
                        question1.putExtra("setTime", setTime);
                        question1.putExtra("Username", userName.getText().toString());
                    }
                    startActivity(question1);
                }
            }
        });
    }
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random()*((max - min) + 1) + min);
    }
}
