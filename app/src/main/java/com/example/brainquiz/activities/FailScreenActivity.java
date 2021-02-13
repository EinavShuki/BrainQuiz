package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.fragments.SaveScoreDialog;
import com.example.brainquiz.utils.Constants;

public class FailScreenActivity extends AppCompatActivity  {
    TextView subTilte, title, levelTv, titleShow, subTileShow;
    Button tryAgain, btnSaveScore;
    String name_activity, lev;
    Space spc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen);

        MediaPlayer sound = MediaPlayer.create(this, R.raw.dramatic);
        sound.start();

        init();
        name_activity = getIntent().getStringExtra(Constants.ACTIVITY_NAME_KEY);

        renderNumMemory();


        tryAgain.setOnClickListener(v->{
            Intent intent = new Intent(FailScreenActivity.this, LastStateNumMemoryActivity.class);
            startActivity(intent);
            finish();
        });

        btnSaveScore.setOnClickListener(v -> {
            SaveScoreDialog saveScoreDialog = new SaveScoreDialog();
            Bundle args = new Bundle();
            int level = getIntent().getIntExtra("level", 1);
            args.putInt(Constants.SCORE_KEY, level);
            args.putString(Constants.SCREEN_KEY, Constants.NUMBERS_MEMORY_TABLE);
            saveScoreDialog.setArguments(args);
            saveScoreDialog.show(getSupportFragmentManager(), Constants.DIALOG_SAVE_SCORE);
        });
    }

    private void init() {
        subTilte = findViewById(R.id.wrong_num);
        title = findViewById(R.id.right_num);
        levelTv = findViewById(R.id.level_tv);
        tryAgain = findViewById(R.id.try_again_btn);
        btnSaveScore = findViewById(R.id.save_score_digits_btn);
        titleShow = findViewById(R.id.title_to_show);
        subTileShow = findViewById(R.id.sub_title_to_show);
        spc = findViewById(R.id.spc);
    }


    private void renderNumMemory() {
        int level = getIntent().getIntExtra("level", 1);
        int wrongNumber = getIntent().getIntExtra("wrong number", 0);
        int rightNumber = getIntent().getIntExtra("right number", 0);
        spc.setVisibility(View.GONE);
        titleShow.setText(R.string.number);
        subTileShow.setText(R.string.your_ans);
        subTileShow.setVisibility(View.VISIBLE);
        subTilte.setVisibility(View.VISIBLE);
        levelTv.setVisibility(View.VISIBLE);
        subTilte.setText(wrongNumber + "");
        title.setText(rightNumber + "");
        lev = getString(R.string.level);
        levelTv.setText(lev + " " + level);

    }

}