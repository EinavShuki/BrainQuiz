package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    Button btnLeaderboards;
    MaterialCardView numMemory;
    MaterialCardView visualMemoryCard;
    MaterialCardView mathRiddle;
    MaterialCardView colorMatcherCard;
    ImageButton volume;
    boolean vol=true ;
    MediaPlayer backvol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MediaPlayer backMusic = MediaPlayer.create(MainActivity.this, R.raw.background_visual_memory);
//        backMusic.setVolume(0.1f, 0.1f);
//        backMusic.setLooping(true);
//        backMusic.start();

        initUi();
        setListeners();
        backvol.setVolume(0.05f, 0.05f);
        backvol.start();
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (vol) {
//            backvol.setVolume(0.05f, 0.05f);
//        } else {
//            backvol.setVolume(0, 0);
//        }
//        backvol.start();
//
//    }

    private void initUi() {
        btnLeaderboards = findViewById(R.id.btn_leaderboards);
        numMemory = findViewById(R.id.num_memory);
        visualMemoryCard = findViewById(R.id.visual_memory_card);
        mathRiddle = findViewById(R.id.math_riddle);
        colorMatcherCard = findViewById(R.id.color_matcher_card);
        volume = findViewById(R.id.volume_btn);
        backvol = MediaPlayer.create(MainActivity.this, R.raw.background_visual_memory);
    }


    private void setListeners() {
        btnLeaderboards.setOnClickListener(view -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, LeaderboardActivity.class));
        });

        numMemory.setOnClickListener(v -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, LastStateNumMemoryActivity.class));
        });

        visualMemoryCard.setOnClickListener(v -> {
            backvol.pause();
            Intent intent = new Intent(MainActivity.this, VisualMemoryActivity.class);
            startActivity(intent);
        });
        mathRiddle.setOnClickListener(v -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, MathRiddlesActivity.class));
        });

        colorMatcherCard.setOnClickListener(v -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, ColorMatcherActivity.class));
        });

        volume.setOnClickListener(v -> {
            if (vol) {
                volume.setImageResource(R.drawable.volume_off);
                backvol.setVolume(0.05f, 0.05f);
            } else {
                volume.setImageResource(R.drawable.volume_up);
                backvol.setVolume(0, 0);
            }
            vol = !vol;
            backvol.start();
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("vol",vol+" ");
        if (vol) {
            volume.setImageResource(R.drawable.volume_off);
            backvol.setVolume(0.05f, 0.05f);
        } else {
            volume.setImageResource(R.drawable.volume_up);
            backvol.setVolume(0, 0);
        }
        backvol.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backvol.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        backvol.pause();
    }
}