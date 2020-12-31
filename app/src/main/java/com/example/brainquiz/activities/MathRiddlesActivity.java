package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.brainquiz.R;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MathRiddlesActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
    int level;
    Random rand = new Random();

    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnZero;
    TextView tvDelete;
    TextView tvAnswer;
    Button Enter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles);

        initUi();
        setListeners();
    }

    private void initUi() {
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
        tvDelete = findViewById(R.id.tv_x);

        Enter = findViewById(R.id.enter);
        tvAnswer = findViewById(R.id.answer);

    }

    private void setListeners() {
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


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("lev", level);
        editor.apply();
    }

    private void appendNumber(String num) {
        if (tvAnswer.getText().length() == 6) {
            return;
        }
        tvAnswer.append(num);
    }

    private void checkAnswer() {
        String ans = tvAnswer.getText().toString();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.tv_x:
                tvAnswer.setText("");
                break;


        }

    }
}