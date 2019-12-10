package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/** Load the main page.*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button myButton = findViewById(R.id.gameStart);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, UserName.class);
                startActivity(myIntent);
            }
        });
        Button quit = findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button ranking = findViewById(R.id.ranking);
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score dialog = new Score(MainActivity.this);
                if (ScoreData.scoreData != null) {
                    for (ScoreData myScore : ScoreData.scoreData) {
                        dialog.addListData(String.format("%s%s%s%s%s", myScore.getQuestionDied(), "  Score: ", myScore.getScore(),
                                "  User: ", myScore.getUserName()));
                    }
                }
                dialog.show();
            }
        });
    }
}

