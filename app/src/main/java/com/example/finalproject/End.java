package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class End extends AppCompatActivity {
    AnimationDrawable animationDrawable;
    Button goTomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        ImageView imageView = findViewById(R.id.animation);
        imageView.setBackgroundResource(R.drawable.animation);
        goTomain = findViewById(R.id.goToMain);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        goTomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(End.this, MainActivity.class);
                startActivity(intent);
                finish();;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animationDrawable.start();
    }
}
