package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;

public class FailScreenActivity extends AppCompatActivity implements View.OnClickListener {
    TextView subTilte, title, levelTv, titleShow, subTileShow;
    Button tryAgain, btnSaveScore;
    String name_activity, lev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen);

        init();
        name_activity = getIntent().getStringExtra("nameActivity");
        switch (name_activity) {
            case "MathRiddlesActivity":
                renderMathRiddle();
                break;
            case "NumberMemorySecActivity":
                renderNumMemory();
                break;
            case "VisualMemoryActivity":
                renderVisualMemory();
                break;
        }


        tryAgain.setOnClickListener(this);

//        btnSaveScore.setOnClickListener(v -> {
//            SaveScoreDialog saveScoreDialog = new SaveScoreDialog();
//            Bundle args = new Bundle();
//            args.putInt(Constants.SCORE_KEY, level);
//            saveScoreDialog.setArguments(args);
//            saveScoreDialog.show(getSupportFragmentManager(), Constants.DIALOG_SAVE_SCORE);
//        });
    }

    private void init() {
        subTilte = findViewById(R.id.wrong_num);
        title = findViewById(R.id.right_num);
        levelTv = findViewById(R.id.level_tv);
        tryAgain = findViewById(R.id.try_again_btn);
        btnSaveScore = findViewById(R.id.save_score_btn);
        titleShow = findViewById(R.id.title_to_show);
        subTileShow = findViewById(R.id.sub_title_to_show);
    }

    private void renderNumMemory() {
        int level = getIntent().getIntExtra("level", 1);
        int wrongNumber = getIntent().getIntExtra("wrong number", 0);
        int rightNumber = getIntent().getIntExtra("right number", 0);
        titleShow.setText(R.string.number);
        subTileShow.setText(R.string.your_ans);
        subTilte.setText(wrongNumber + "");
        title.setText(rightNumber + "");
        lev = getString(R.string.level);
        levelTv.setText(lev + " " + level);

    }

    private void renderMathRiddle() {
        int level = getIntent().getIntExtra("level", 1);
        title.setText(level + "");
        titleShow.setText(R.string.score);
        subTileShow.setText(R.string.your_record);
    }

    private void renderVisualMemory() {
        int level = getIntent().getIntExtra("level", 1);
        title.setText(level + "");
        titleShow.setText(R.string.score);
        subTileShow.setText(R.string.your_record);
    }

    @Override
    public void onClick(View v) {
        switch (name_activity) {
            case "NumberMemorySecActivity":
                Intent intent = new Intent(FailScreenActivity.this, LastStateNumMemoryActivity.class);
                startActivity(intent);
                finish();
                break;
            case "MathRiddlesActivity":
                Intent intent2 = new Intent(FailScreenActivity.this, MathRiddlesActivity.class);
                startActivity(intent2);
                finish();
                break;
            case "VisualMemoryActivity":
                Intent intent3 = new Intent(FailScreenActivity.this, VisualMemoryActivity.class);
                intent3.putExtra("level", 1);
                intent3.putExtra("strike", 1);
                startActivity(intent3);
                finish();
                break;
        }
    }
}