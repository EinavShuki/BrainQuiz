package com.example.brainquiz.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class
MathRiddlesActivity extends AppCompatActivity implements View.OnClickListener, Animator.AnimatorListener {
    SharedPreferences sp;
    List<Pair<String, String>> levelStart = new ArrayList<>(Constants.riddlesLevelStart);
    List<Pair<String, String>> levelMiddle = new ArrayList<>(Constants.riddlesLevelMiddle);
    List<Pair<String, String>> levelHigh = new ArrayList<>(Constants.riddlesLevelHigh);

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
    TextView riddle;
    TextView Count;
    TextView Timer;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles);

        ConstraintLayout root = findViewById(R.id.math_root_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) root.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.stripe_anim);
        LinearLayout stripes = findViewById(R.id.stripes);
        stripes.startAnimation(animation);

        initUi();
        setListeners();
        ecersice[0] =levelStart.get(random_num).first;
        ecersice[1] =levelStart.get(random_num).second;

        showRiddle(ecersice[0]);


        new CountDownTimer(61000, 1000) {

            public void onTick(long millisUntilFinished) {
                Timer.setText(String.format("%02d:%02d", millisUntilFinished/1000/ 60, millisUntilFinished /1000% 60));
                if(millisUntilFinished/1000% 60==10){
                    Timer.setTextColor(getColor(R.color.red));
                }
                time = millisUntilFinished /1000% 60;
            }

            public void onFinish() {
                Intent intent = new Intent(MathRiddlesActivity.this, FailScreenActivity.class);
                intent.putExtra("level", Integer.parseInt(Count.getText().toString()));
                intent.putExtra(Constants.ACTIVITY_NAME_KEY,Constants.MATH_TITLE);
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
        riddle = findViewById(R.id.calculate);
        Count = findViewById(R.id.count);
        Enter = findViewById(R.id.enter);
        tvAnswer = findViewById(R.id.answer);
        Timer = findViewById(R.id.timer);
        animationView = findViewById(R.id.correct_anim);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOne-Regular.ttf");
        riddle.setTypeface(typeface);
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
                    playSuccess();
                    tvAnswer.setText("");
                    Count.setText( String.valueOf(Integer.parseInt(Count.getText().toString())+1));
                    if( time>40 && Integer.parseInt(Count.getText().toString())<5){
                        random_num = new Random().nextInt(levelStart.size());
                        ecersice[0] = levelStart.get(random_num).first;
                        ecersice[1] = levelStart.get(random_num).second;
                        levelStart.remove(random_num);
                        Log.i("sssiiizzeee", String.valueOf(levelStart.size()));

                    }
                    else if((time<=40 && time>20 || (Integer.parseInt(Count.getText().toString())>=5)) && Integer.parseInt(Count.getText().toString())<10) {
                        random_num = new Random().nextInt(levelMiddle.size());
                        ecersice[0] = levelMiddle.get(random_num).first;
                        ecersice[1] = levelMiddle.get(random_num).second;
                        levelMiddle.remove(random_num);
                    }
                    else{
                        random_num = new Random().nextInt(levelHigh.size());
                        ecersice[0] = levelHigh.get(random_num).first;
                        ecersice[1] = levelHigh.get(random_num).second;
                        levelHigh.remove(random_num);
                    }
                    showRiddle(ecersice[0]);


                } else {
                    playFailure();
                }
                tvAnswer.setText("");
            }

        });


    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences.Editor editor = sp.edit();
//
//        editor.apply();
//    }

    private void playSuccess(){
        animationView.setVisibility(View.VISIBLE);
        animationView.playAnimation();
        animationView.addAnimatorListener(this);
    }
    private void playFailure(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.wrong);
        mp.start();
    }
    private void playTap(){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.tap);
        mp.start();
    }
    private void showRiddle(String text){
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        riddle.setText(text);
        riddle.startAnimation(tvAnimation);
    }

    private void appendNumber(String num) {
        if (tvAnswer.getText().length() == 6) {
            return;
        }
        playTap();
        tvAnswer.append(num);
    }

    private void checkAnswer() {
        String ans = tvAnswer.getText().toString();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm shutdown").setMessage("are you yiyuihj").setPositiveButton("yes,get out", (dialog, which) -> MathRiddlesActivity.super.onBackPressed()).setNegativeButton("stay..",null).show();
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
            case R.id.btn_0:
                appendNumber("0");
                break;
            case R.id.tv_x:
                tvAnswer.setText("");
                break;


        }

    }

    @Override
    public void onAnimationStart(Animator animator) {
        animationView.setVisibility(View.VISIBLE);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.correct_choice);
        mp.start();
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        animationView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}