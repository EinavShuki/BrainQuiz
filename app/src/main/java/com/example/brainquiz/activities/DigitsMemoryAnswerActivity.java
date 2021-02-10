package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

public class DigitsMemoryAnswerActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
    int number, lev;
    Button submitBtn, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero, delete;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits_memory_answer);

        init();
        lev = getIntent().getIntExtra("level", 1);
        number = getIntent().getIntExtra("number", 5);

        Toolbar toolbar = findViewById(R.id.level_toolbar);
        toolbar.setTitle(toolbar.getTitle() + " " + lev);
        sp = getSharedPreferences("RememberDetails", MODE_PRIVATE);
        answer.requestFocus();
        setListeners();

    }

    private void init() {
        submitBtn = findViewById(R.id.submit_btn);
        answer = findViewById(R.id.num_input);
        btnOne = findViewById(R.id.btn_1);
        btnTwo = findViewById(R.id.btn_2);
        btnThree = findViewById(R.id.btn_3);
        btnFour = findViewById(R.id.btn_4);
        btnFive = findViewById(R.id.btn_5);
        btnSix = findViewById(R.id.btn_6);
        btnSeven = findViewById(R.id.btn_7);
        btnEight = findViewById(R.id.btn_8);
        btnNine = findViewById(R.id.btn_9);
        btnZero = findViewById(R.id.btn_0);
        delete = findViewById(R.id.tv_x);
    }

    private void playTap() {
        MediaPlayer mp = MediaPlayer.create(DigitsMemoryAnswerActivity.this, R.raw.tap);
        mp.start();
    }

    private void setListeners() {
        submitBtn.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    private void appendNumber(String num) {
        playTap();
        if (num.equals("nothing"))
            answer.setText("");
        else
            answer.append(num);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                if (!answer.getText().toString().equals("") && TextUtils.isDigitsOnly(answer.getText())) {
                    //the number from the user
                    int num = Integer.parseInt(answer.getText().toString());
                    if (number == num) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Intent intent = new Intent(DigitsMemoryAnswerActivity.this, FailScreenActivity.class);
                        intent.putExtra("wrong number", num);
                        intent.putExtra("right number", number);
                        intent.putExtra("level", lev);
                        intent.putExtra(Constants.ACTIVITY_NAME_KEY, Constants.NUMBERS_MEMORY_TITLE);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            case R.id.btn_1:
                appendNumber("1");
                break;
            case R.id.btn_2:
                appendNumber("2");
                break;
            case R.id.btn_3:
                appendNumber("3");
                break;
            case R.id.btn_4:
                appendNumber("4");
                break;
            case R.id.btn_5:
                appendNumber("5");
                break;
            case R.id.btn_6:
                appendNumber("6");
                break;
            case R.id.btn_7:
                appendNumber("7");
                break;
            case R.id.btn_8:
                appendNumber("8");
                break;
            case R.id.btn_9:
                appendNumber("9");
                break;
            case R.id.btn_0:
                appendNumber("0");
                break;
            case R.id.tv_x:
                appendNumber("nothing");
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("num", number);
        editor.putInt("lev", lev);
        editor.apply();
    }

}