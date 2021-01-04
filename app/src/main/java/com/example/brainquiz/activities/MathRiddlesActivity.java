package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class
MathRiddlesActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
    List<Pair<String, String>> levelStart = Constants.riddlesLevelStart;
    List<Pair<String, String>> levelMiddle = Constants.riddlesLevelMiddle;
    List<Pair<String, String>> levelHigh = Constants.riddlesLevelHigh;
    String[] ecersice = {"", ""};

    long time;

    int random_num = new Random().nextInt(levelStart.size());


    int level=0;
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
    TextView Calculate;
    TextView Count;
    TextView Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles);
        initUi();
        setListeners();
        ecersice[0] =levelStart.get(random_num).first;
        ecersice[1] =levelStart.get(random_num).second;
        Calculate.setText(ecersice[0]);



        new CountDownTimer(11000, 1000) {

            public void onTick(long millisUntilFinished) {
                Timer.setText(String.format("%02d:%02d", millisUntilFinished/1000/ 60, millisUntilFinished /1000% 60));
                time = millisUntilFinished /1000% 60;
            }

            public void onFinish() {
                Intent intent = new Intent(MathRiddlesActivity.this, FailScreenActivity.class);
                intent.putExtra("level", Integer.parseInt(Count.getText().toString()));
                intent.putExtra("nameActivity","MathRiddlesActivity");
                startActivity(intent);
                Timer.setText("done!");
            }
        }.start();


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
        Calculate = findViewById(R.id.calculate);
        Count = findViewById(R.id.count);
        Enter = findViewById(R.id.enter);
        tvAnswer = findViewById(R.id.answer);
        Timer = findViewById(R.id.timer);

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
        tvDelete.setOnClickListener(this);

        Enter.setOnClickListener(v -> {
            if(!tvAnswer.getText().toString().equals("")){
                if(tvAnswer.getText().toString().equals(ecersice[1])){
                    Count.setText( String.valueOf(Integer.parseInt(Count.getText().toString())+1));
                    if( time>40) {
                        random_num = new Random().nextInt(levelStart.size());
                        ecersice[0] = levelStart.get(random_num).first;
                        ecersice[1] = levelStart.get(random_num).second;

                    }
                    else if(time<=40 && time>20) {
                        random_num = new Random().nextInt(levelMiddle.size());
                        ecersice[0] = levelMiddle.get(random_num).first;
                        ecersice[1] = levelMiddle.get(random_num).second;
                    }
                    else{
                        random_num = new Random().nextInt(levelHigh.size());
                        ecersice[0] = levelHigh.get(random_num).first;
                        ecersice[1] = levelHigh.get(random_num).second;
                    }
                    Calculate.setText(ecersice[0]);
                    tvAnswer.setText("");

                }
            }

        });


    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences.Editor editor = sp.edit();
//        editor.apply();
//    }

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