package com.example.brainquiz.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

public class DigitsMemoryActivity extends AppCompatActivity {
    final int NUMBER_REQUEST = 1;
    LinearLayout mainLayout;
    ProgressBar progressBar;
    Intent intent;
    int numberToShow, lev;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_memory);

        lev = getIntent().getIntExtra("level", 1);//from itself  or LastState
        numberToShow = getIntent().getIntExtra("number", 5);//from NumberMemorySecActivity or LastState

        Toolbar toolbar = findViewById(R.id.level_toolbar);
        toolbar.setTitle(toolbar.getTitle() + " " + lev);

        TextView numTv = findViewById(R.id.tv_number);
        numTv.setText(numberToShow + "");
        if (numTv.length() == 7)
            numTv.setTextSize(30);
        if (numTv.length() > 7)
            numTv.setTextSize(25);

        progressBar = findViewById(R.id.progress_timer);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);

        if (numTv.length() > 7)
            progressAnimator.setDuration(2500);
        else if (numTv.length() > 5)
            progressAnimator.setDuration(2000);
        else
            progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        intent = new Intent(this, DigitsMemoryAnswerActivity.class);
        intent.putExtra("number", numberToShow);
        intent.putExtra("level", lev);

        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivityForResult(intent, NUMBER_REQUEST);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (lev == 26) {
            intent = new Intent(DigitsMemoryActivity.this, WinScreenVisualMemoryActivity.class);
            intent.putExtra(Constants.ACTIVITY_NAME_KEY, Constants.NUMBERS_MEMORY_TITLE);
            startActivity(intent);
        } else if (requestCode == NUMBER_REQUEST) {
            if (resultCode == RESULT_OK) {
                intent = new Intent(DigitsMemoryActivity.this, DigitsMemoryActivity.class);
                intent.putExtra("number", numberToShow * 2 + 1);
                intent.putExtra("level", lev + 1);
                startActivity(intent);
            }
        }
        finish();
    }
}