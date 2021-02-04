package com.example.brainquiz.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainquiz.R;

public class NumberMemoryActivity extends AppCompatActivity {
    final int NUMBER_REQUEST = 1;
    LinearLayout mainLayout;
    ProgressBar progressBar;
    Intent intent;
    int numberToShow, lev;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory);

        mainLayout=findViewById(R.id.main_layout);
        //loading dynamic background
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        lev = getIntent().getIntExtra("level", 1);//from itself  or LastState
        numberToShow = getIntent().getIntExtra("number", 5);//from NumberMemorySecActivity or LastState

        Toolbar toolbar=findViewById(R.id.level_toolbar);
        toolbar.setTitle(toolbar.getTitle()+" "+lev);

        TextView numTv = findViewById(R.id.numtv);
        numTv.setText(numberToShow + "");

        progressBar = findViewById(R.id.progress_bar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
        if (numTv.length() > 6)
            progressAnimator.setDuration(2000);
        else
            progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        intent = new Intent(this, NumberMemorySecActivity.class);
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
        if (requestCode == NUMBER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(NumberMemoryActivity.this, NumberMemoryActivity.class);
                intent.putExtra("number", numberToShow * 2 + 1);
                intent.putExtra("level", lev + 1);
                startActivity(intent);
            }

            finish();
        }
    }
}