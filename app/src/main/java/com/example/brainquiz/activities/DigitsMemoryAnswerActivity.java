package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

public class DigitsMemoryAnswerActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etAnswer;
    long number;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_memory_answer);

        number = getIntent().getLongExtra(Constants.NUMBER_KEY, 0);
        level = getIntent().getIntExtra(Constants.LEVEL_KEY, 1);

        etAnswer = findViewById(R.id.et_answer);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(view -> {
            String answer = etAnswer.getText().toString();
            if (!answer.isEmpty()){
                if(Long.parseLong(answer) == number){
                    Intent intent = new Intent(DigitsMemoryAnswerActivity.this, DigitsMiddleActivity.class);
                    intent.putExtra(Constants.ANSWER_KEY, answer);
                    intent.putExtra(Constants.ANSWER_RESULT, true);
                    intent.putExtra(Constants.NUMBER_KEY, number);
                    intent.putExtra(Constants.LEVEL_KEY, ++level);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        });
    }

}