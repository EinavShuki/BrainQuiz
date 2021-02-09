package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DigitsMemoryActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView tvNumber;
    ObjectAnimator progressAnimator;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_memory);
        initUi();
        level = getIntent().getIntExtra(Constants.LEVEL_KEY, 1);
        showNumber();
    }

    private void initUi(){
        progressBar = findViewById(R.id.progress_timer);
        tvNumber = findViewById(R.id.tv_number);
    }

    private void showNumber(){
        progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
        progressAnimator.setDuration(Constants.MEDIUM_DURATION);

        long number = generateRandomNumber(level);
        tvNumber.setText(String.valueOf(number));

        progressAnimator.start();
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(DigitsMemoryActivity.this, DigitsMemoryAnswerActivity.class);
                intent.putExtra(Constants.NUMBER_KEY, number);
                intent.putExtra(Constants.LEVEL_KEY, level);
                startActivity(intent);
                finish();
            }
        });
    }

    private long generateRandomNumber(int n) {
        long min = (long) Math.pow(10, n - 1);
        return ThreadLocalRandom.current().nextLong(min, min * 10);
    }

    @Override
    public void onBackPressed() {
        if (progressAnimator.isStarted()){
            progressAnimator.pause();
            progressAnimator.removeAllListeners();
        }
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        if (progressAnimator.isStarted()){
            progressAnimator.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (progressAnimator.isStarted()){
            progressAnimator.removeAllListeners();
        }
        super.onDestroy();
    }
}