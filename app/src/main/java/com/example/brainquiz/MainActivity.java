package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    Button btnLeaderboards;
    MaterialCardView numMemory;
    MaterialCardView visualMemoryCard;



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


    }


    private void setListeners() {
        btnLeaderboards.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LeaderboardActivity.class)));

        numMemory.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, LastStateNumMemoryActivity.class)));
        visualMemoryCard.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MathRiddlesActivity.class)));

    }
}