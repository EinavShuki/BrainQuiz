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
    }


    private void setListeners() {
        btnLeaderboards.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LeaderboardActivity.class)));

        numMemory.setOnClickListener(v ->{
            Intent intent=new Intent(MainActivity.this, LastStateNumMemoryActivity.class);
            startActivity(intent);
        });

    }
}