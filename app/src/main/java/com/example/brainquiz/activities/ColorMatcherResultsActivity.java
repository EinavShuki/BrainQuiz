package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.google.android.material.card.MaterialCardView;

public class ColorMatcherResultsActivity extends AppCompatActivity {

    TextView tvLongestRun, tvScore, tvCorrect;
    MaterialCardView cvLongestRun, cvReactionTime, cvScore;

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
        cvScore.startAnimation(centerAnimation);

        tvLongestRun = findViewById(R.id.tv_longest_run);
        tvScore = findViewById(R.id.tv_color_match_score);
        tvCorrect = findViewById(R.id.tv_correct_ans);

        int longestRun = getIntent().getIntExtra(Constants.LONGEST_RUN_KEY, 0);
        String score = getIntent().getStringExtra(Constants.SCORE_KEY);
        int correct = getIntent().getIntExtra(Constants.CORRECT_KEY, 0);

        tvLongestRun.setText(String.valueOf(longestRun));
        tvScore.setText(score);
        tvCorrect.setText(String.valueOf(correct));

    }
}