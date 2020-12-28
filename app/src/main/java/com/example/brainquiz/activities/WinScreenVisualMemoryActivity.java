package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import com.example.brainquiz.R;

public class WinScreenVisualMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen_visual_memory);

        MediaPlayer applause=MediaPlayer.create(this,R.raw.applause);
        applause.start();

        Button homeBtn=findViewById(R.id.home_btn);
        Button saveBtn=findViewById(R.id.save_score_btn);

        homeBtn.setOnClickListener(v->{
            Intent intent=new Intent(WinScreenVisualMemoryActivity.this, MainActivity.class);
            applause.stop();
            startActivity(intent);
            finish();
        });
    }
}