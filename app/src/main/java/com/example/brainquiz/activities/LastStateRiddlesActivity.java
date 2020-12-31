package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.brainquiz.R;

public class LastStateRiddlesActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_state_riddles);
        Button newGameBtn=findViewById(R.id.new_game_btn);
        Button lastPointBtn=findViewById(R.id.continue_btn);
        intent=new Intent(LastStateRiddlesActivity.this,MathRiddlesActivity.class);
        sp = getSharedPreferences("RememberDetails1",MODE_PRIVATE);

        newGameBtn.setOnClickListener(v->{
            intent.putExtra("lev_riddles",1);
            startActivity(intent);
        });
        lastPointBtn.setOnClickListener(v->{
            intent.putExtra("lev_riddles",sp.getInt("lev",1));
            startActivity(intent);
        });

    }
}