package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.fragments.SaveScoreDialog;
import com.example.brainquiz.utils.Constants;
import com.google.android.material.card.MaterialCardView;

public class ColorMatcherResultsActivity extends AppCompatActivity {

    TextView tvLongestRun, tvScore, tvCorrect;
    MaterialCardView cvLongestRun, cvReactionTime, cvScore;
    Button btnSaveScore, btnTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matcher_results);

        Animation leftAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.left_to_right);
        Animation rightAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.right_to_left);
        Animation centerAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        cvLongestRun = findViewById(R.id.longest_run_card);
        cvReactionTime = findViewById(R.id.reaction_time_card);
        cvScore = findViewById(R.id.score_colors_card);

        cvLongestRun.startAnimation(rightAnimation);
        cvReactionTime.startAnimation(leftAnimation);
//        cvScore.startAnimation(centerAnimation);

        tvLongestRun = findViewById(R.id.tv_longest_run);
        tvScore = findViewById(R.id.tv_color_match_score);
        tvCorrect = findViewById(R.id.tv_correct_ans);

        int longestRun = getIntent().getIntExtra(Constants.LONGEST_RUN_KEY, 0);
        String score = getIntent().getStringExtra(Constants.SCORE_KEY);
        int correct = getIntent().getIntExtra(Constants.CORRECT_KEY, 0);

        tvLongestRun.setText(String.valueOf(longestRun));
        tvScore.setText(score);
        tvCorrect.setText(String.valueOf(correct));

        btnSaveScore = findViewById(R.id.save_score_colors_btn);
        btnTryAgain = findViewById(R.id.try_again_colors_btn);

        btnSaveScore.setOnClickListener(v -> {
            SaveScoreDialog saveScoreDialog = new SaveScoreDialog();
            Bundle args = new Bundle();
            args.putInt(Constants.SCORE_KEY, Integer.parseInt(score));
            args.putString(Constants.SCREEN_KEY, Constants.COLOR_MATCH_TABLE);
            saveScoreDialog.setArguments(args);
            saveScoreDialog.show(getSupportFragmentManager(), Constants.DIALOG_SAVE_SCORE);
        });

        btnTryAgain.setOnClickListener(v -> {
            startActivity(new Intent(this, ColorMatcherActivity.class));
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}