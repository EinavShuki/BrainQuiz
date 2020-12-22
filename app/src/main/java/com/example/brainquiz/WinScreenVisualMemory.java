package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import com.example.brainquiz.activities.MainActivity;

import java.io.BufferedReader;

public class WinScreenVisualMemory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen_visual_memory);

        MediaPlayer applause=MediaPlayer.create(this,R.raw.applause);
        applause.start();

        Button homeBtn=findViewById(R.id.home_btn);
        Button saveBtn=findViewById(R.id.save_score_btn);

        homeBtn.setOnClickListener(v->{
            Intent intent=new Intent(WinScreenVisualMemory.this, MainActivity.class);
            applause.stop();
            startActivity(intent);
            finish();
        });
    }
}