package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.SaveScoreDialog;

public class FailScreenNumberMemoryActivity extends AppCompatActivity {

    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen_number_memory);

        int wrongNumber=getIntent().getIntExtra("wrong number",0);
        int rightNumber=getIntent().getIntExtra("right number",0);
        level=getIntent().getIntExtra("level",1);

        Button tryAgain=findViewById(R.id.try_again_btn);
        Button btnSaveScore = findViewById(R.id.save_score_btn);

        TextView wrongNumTv=findViewById(R.id.wrong_num);
        TextView rightNumTv=findViewById(R.id.right_num);
        TextView levelTv=findViewById(R.id.level_tv);

        wrongNumTv.setText(wrongNumber+"");
        rightNumTv.setText(rightNumber+"");
        String lev=getString(R.string.level);
        levelTv.setText(lev+" "+level);

        tryAgain.setOnClickListener(v -> {
            Intent intent=new Intent(FailScreenNumberMemoryActivity.this, LastStateNumMemoryActivity.class);
            startActivity(intent);
            finish();
        });

        btnSaveScore.setOnClickListener(v -> {
            SaveScoreDialog saveScoreDialog = new SaveScoreDialog();
            Bundle args = new Bundle();
            args.putInt("SCORE", level);
            saveScoreDialog.setArguments(args);
            saveScoreDialog.show(getSupportFragmentManager(), "save score dialog");
        });
    }
}