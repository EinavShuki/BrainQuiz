package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.fragments.SaveScoreDialog;

public class FailScreenRiddlesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen_riddles);

        String yourScore=getIntent().getStringExtra("score");

        Button newGame=findViewById(R.id.new_game_btn);
        Button btnSaveScore = findViewById(R.id.save_score_btn);//פה צריך לשמור לDB
        TextView yourScoreTV=findViewById(R.id.your_score);
        TextView theBestScore =findViewById(R.id.wrong_num);
        yourScoreTV.setText(yourScore);;


        newGame.setOnClickListener(v -> {
            Intent intent=new Intent(FailScreenRiddlesActivity.this, MathRiddlesActivity.class);
            startActivity(intent);
            finish();
        });


}}