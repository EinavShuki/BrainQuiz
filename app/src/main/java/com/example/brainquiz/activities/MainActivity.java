package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.brainquiz.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    Button btnLeaderboards;
    MaterialCardView numMemory;
    MaterialCardView visualMemoryCard;
    MaterialCardView mathRiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        setListeners();
    }

    private void initUi() {
        btnLeaderboards = findViewById(R.id.btn_leaderboards);
        numMemory = findViewById(R.id.num_memory);
        visualMemoryCard = findViewById(R.id.visual_memory_card);
        mathRiddle = findViewById(R.id.math_riddle);
    }


    private void setListeners() {
        btnLeaderboards.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LeaderboardActivity.class)));
        numMemory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LastStateNumMemoryActivity.class)));
        visualMemoryCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VisualMemoryActivity.class);
            intent.putExtra("level", 1);
            intent.putExtra("strike", 1);
            startActivity(intent);
        });
        mathRiddle.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MathRiddlesActivity.class))
        );



    }

}