package com.example.brainquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberMemoryActivity extends AppCompatActivity {
    final int NUMBER_REQUEST = 1;
    ProgressBar progressBar;
    Intent intent;
    int numberToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory);

        numberToShow = getIntent().getIntExtra("number", 5);//from NumberMemorySecActivity
        int lastNumberToShow=getIntent().getIntExtra("num",0);//from LastState
        numberToShow= Math.max(numberToShow, lastNumberToShow);
        TextView numTv = findViewById(R.id.numtv);
        numTv.setText(numberToShow + "");
        progressBar = findViewById(R.id.progress_bar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 0);
        if (numTv.length() > 6)
            progressAnimator.setDuration(1500);
        else
            progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
        intent = new Intent(this, NumberMemorySecActivity.class);
        intent.putExtra("number", numberToShow);
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
                startActivity(intent);
                finish();
            } else if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Looser", Toast.LENGTH_SHORT).show();
        }
    }
}