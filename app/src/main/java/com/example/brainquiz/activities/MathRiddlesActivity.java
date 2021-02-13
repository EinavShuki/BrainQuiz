package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.SharedPrefsManager;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class
MathRiddlesActivity extends AppCompatActivity implements View.OnClickListener {

    List<Pair<String, String>> level1 = new ArrayList<>(Constants.riddlesLevel1);
    List<Pair<String, String>> level2 = new ArrayList<>(Constants.riddlesLevel2);
    List<Pair<String, String>> level3 = new ArrayList<>(Constants.riddlesLevel3);
    List<Pair<String, String>> level4 = new ArrayList<>(Constants.riddlesLevel4);
    List<Pair<String, String>> level5 = new ArrayList<>(Constants.riddlesLevel5);
    String[] exercise = {"", ""};
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
    TextView Level;
    MaterialCardView cvRiddleCard;
    LottieAnimationView correctAnimView;
    LottieAnimationView wrongAnimView;
    long time;
    long mTimeleft=91000;
    int random_num = new Random().nextInt(level1.size());
    int asked = 0;
    float timeWhenQuestionShowed = 30;
    float timeWhenUserReacted;
    float totalReactionTime = 0;
    CountDownTimer countDownTimer;
    private void startTimer() {
        countDownTimer = new CountDownTimer(mTimeleft, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimeleft = millisUntilFinished;
                Timer.setText(String.format("%02d:%02d", millisUntilFinished / 1000 / 60, millisUntilFinished / 1000 % 60));
                if (mTimeleft/1000==10) {
                    Timer.setTextColor(getColor(R.color.red));
                }
                time = millisUntilFinished / 1000 % 60;
            }


            public void onFinish() {
                Intent intent = new Intent(MathRiddlesActivity.this, MathRiddlesResultsActivity.class);
                float accuracy = (Float.parseFloat(Count.getText().toString()) / (asked - 1)) * 100;

                intent.putExtra(Constants.ACCURACY_KEY, String.valueOf((int) accuracy));
                intent.putExtra(Constants.REACTION_TIME_KEY, String.format("%.2f", (totalReactionTime / asked)));
                intent.putExtra(Constants.MATH_SCORE_KEY, Count.getText().toString());

                SharedPrefsManager.saveInLastScores(Count.getText().toString(), MathRiddlesActivity.this,Constants.MATH_SCORES_KEY,Constants.MATH_SCORES_PREFS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                Timer.setText("Done!");
            }
        }.start();
    }

    @Override
    @SuppressLint("DefaultLocale")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles);

        ConstraintLayout root = findViewById(R.id.math_root_layout);

//        AnimationDrawable animationDrawable = (AnimationDrawable) root.getBackground();
//        animationDrawable.setEnterFadeDuration(10);
//        animationDrawable.setExitFadeDuration(5000);
//        animationDrawable.start();

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.stripe_anim);
//        LinearLayout stripes = findViewById(R.id.stripes);
//        stripes.startAnimation(animation);

        initUi();
        setListeners();
        exercise[0] =level1.get(random_num).first;
        exercise[1] =level1.get(random_num).second;

        ++asked;
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_top_to_bottom);
        riddle.setText(exercise[0]);
        cvRiddleCard.startAnimation(tvAnimation);
        startTimer();


    };



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

        tvAnswer = findViewById(R.id.answer);
        Timer = findViewById(R.id.timer);
        Level = findViewById(R.id.level);
        cvRiddleCard = findViewById(R.id.card_riddle);
        correctAnimView = findViewById(R.id.correct_anim);
        wrongAnimView = findViewById(R.id.wrong_anim);
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

    }
    private  void CheckStep(){
        if(tvAnswer.getText().toString().equals(exercise[1])){
            // Calculate reaction time
            timeWhenUserReacted = Float.parseFloat(Timer.getText().toString().substring(3));
            float reactionTime = timeWhenQuestionShowed - timeWhenUserReacted;
            totalReactionTime += reactionTime;
            playSuccess();
            tvAnswer.setText("");
            Count.setText( String.valueOf(Integer.parseInt(Count.getText().toString())+1));
            showRiddle();
            tvAnswer.setText("");
        }
        else if(tvAnswer.getText().toString().length()>=exercise[1].length()) {
            playFailure();
            showRiddle();
            tvAnswer.setText("");
        }

    }

    private void playSuccess(){
        correctAnimView.setVisibility(View.VISIBLE);
        correctAnimView.playAnimation();
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.correct_choice);
        mp.start();
        correctAnimView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                correctAnimView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                correctAnimView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
    private void playFailure(){
        wrongAnimView.setVisibility(View.VISIBLE);
        wrongAnimView.playAnimation();
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.wrong);
        mp.start();
        wrongAnimView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                wrongAnimView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                wrongAnimView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }
    private void playTap(){
        MediaPlayer mp = MediaPlayer.create(MathRiddlesActivity.this, R.raw.tap);
        mp.start();
    }
    private void showRiddle(){

        if( mTimeleft/1000>72 ){
            Level.setText("1/5");
            random_num = new Random().nextInt(level1.size());
            exercise[0] = level1.get(random_num).first;
            exercise[1] = level1.get(random_num).second;
            level1.remove(random_num);
        }
        else if((mTimeleft/1000<=72 && mTimeleft/1000>54)) {
            Level.setText("2/5");
            random_num = new Random().nextInt(level2.size());
            exercise[0] = level2.get(random_num).first;
            exercise[1] = level2.get(random_num).second;
            level2.remove(random_num);
        }
        else if((mTimeleft/1000<=54 && mTimeleft/1000>36)) {
            Level.setText("3/5");
            random_num = new Random().nextInt(level3.size());
            exercise[0] = level3.get(random_num).first;
            exercise[1] = level3.get(random_num).second;
            level3.remove(random_num);
        }
        else if((mTimeleft/1000<=36 && mTimeleft/1000>18 )) {
            Level.setText("4/5");
            random_num = new Random().nextInt(level4.size());
            exercise[0] = level4.get(random_num).first;
            exercise[1] = level4.get(random_num).second;
            level4.remove(random_num);
        }
        else{
            Level.setText("5/5");
            random_num = new Random().nextInt(level5.size());
            exercise[0] = level5.get(random_num).first;
            exercise[1] = level5.get(random_num).second;
            level5.remove(random_num);
        }

        ++asked;
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_top_to_bottom);

        riddle.setText(exercise[0]);
        cvRiddleCard.startAnimation(tvAnimation);

        timeWhenQuestionShowed = Float.parseFloat(Timer.getText().toString().substring(3));
    }

    private void appendNumber(String num) {
        if (tvAnswer.getText().length() == 6) {
            return;
        }
        playTap();
        tvAnswer.append(num);
    }

    @Override
    public void onBackPressed() {
        if(countDownTimer  != null) {
            countDownTimer.cancel();
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();

        }
    }

    @Override
    protected void onPause() {
        if(countDownTimer  != null) {
            countDownTimer.cancel();}
        finish();
        super.onPause();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                appendNumber("1");
                CheckStep();
                break;
            case R.id.btn_2:
                appendNumber("2");
                CheckStep();
                break;
            case R.id.btn_3:
                appendNumber("3");
                CheckStep();
                break;
            case R.id.btn_4:
                appendNumber("4");
                CheckStep();
                break;
            case R.id.btn_5:
                appendNumber("5");
                CheckStep();
                break;
            case R.id.btn_6:
                appendNumber("6");
                CheckStep();
                break;
            case R.id.btn_7:
                appendNumber("7");
                CheckStep();
                break;
            case R.id.btn_8:
                appendNumber("8");
                CheckStep();
                break;
            case R.id.btn_9:
                appendNumber("9");
                CheckStep();
                break;
            case R.id.btn_0:
                appendNumber("0");
                CheckStep();
                break;
            case R.id.tv_x:
                tvAnswer.setText("");
                break;


        }

    }
}