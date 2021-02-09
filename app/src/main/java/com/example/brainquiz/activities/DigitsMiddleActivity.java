package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

public class DigitsMiddleActivity extends AppCompatActivity {

    TextView tvAnswer, tvNumber, tvLevel;
    Button btnNext;
    String answer;
    int level;
    long number;
    boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_middle);

        level = getIntent().getIntExtra(Constants.LEVEL_KEY, 1);
        number = getIntent().getLongExtra(Constants.NUMBER_KEY, 0);
        answer = getIntent().getStringExtra(Constants.ANSWER_KEY);

        tvNumber = findViewById(R.id.tv_number_query);
        tvAnswer = findViewById(R.id.tv_number_answer);
        tvLevel = findViewById(R.id.v_current_level);
        btnNext = findViewById(R.id.btn_next);

        tvNumber.setText(String.valueOf(number));
        tvAnswer.setText(answer);
        tvLevel.setText(String.valueOf(level));

        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(DigitsMiddleActivity.this, DigitsMemoryActivity.class);
            intent.putExtra(Constants.LEVEL_KEY, level);
            startActivity(intent);
            finish();
        });
    }
}