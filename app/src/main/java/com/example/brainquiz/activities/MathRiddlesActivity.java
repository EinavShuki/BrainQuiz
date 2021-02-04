package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class
MathRiddlesActivity extends AppCompatActivity implements View.OnClickListener {

    List<Pair<String, String>> levelStart = new ArrayList<>(Constants.riddlesLevelStart);
    List<Pair<String, String>> levelMiddle = new ArrayList<>(Constants.riddlesLevelMiddle);
    List<Pair<String, String>> levelHigh = new ArrayList<>(Constants.riddlesLevelHigh);
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
    LottieAnimationView correctAnimView;
    LottieAnimationView wrongAnimView;
    long time;
    int random_num = new Random().nextInt(levelStart.size());
    int asked = 0;
    float timeWhenQuestionShowed = 30;
    float timeWhenUserReacted;
    float totalReactionTime = 0;

    @Override
    @SuppressLint("DefaultLocale")
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
        exercise[0] =levelStart.get(random_num).first;
        exercise[1] =levelStart.get(random_num).second;

        ++asked;
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        riddle.setText(exercise[0]);
        riddle.startAnimation(tvAnimation);

        new CountDownTimer(31000, 1000) {
            public void onTick(long millisUntilFinished) {
                Timer.setText(String.format("%02d:%02d", millisUntilFinished/1000/ 60, millisUntilFinished /1000% 60));
                if(millisUntilFinished/1000% 60==10){
                    Timer.setTextColor(getColor(R.color.red));
                }
                time = millisUntilFinished /1000% 60;
            }

            public void onFinish() {
                Intent intent = new Intent(MathRiddlesActivity.this, MathRiddlesResultsActivity.class);
                float accuracy =  (Float.parseFloat(Count.getText().toString()) / (asked - 1) ) * 100;

                intent.putExtra(Constants.ACCURACY_KEY, String.valueOf((int)accuracy));
                intent.putExtra(Constants.REACTION_TIME_KEY,  String.format("%.2f", (totalReactionTime / asked)));
                intent.putExtra(Constants.MATH_SCORE_KEY, Count.getText().toString());

                SharedPrefsManager.saveInLastScores(Count.getText().toString(), MathRiddlesActivity.this);

                startActivity(intent);
                Timer.setText("Done!");
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
        correctAnimView = findViewById(R.id.correct_anim);
        wrongAnimView = findViewById(R.id.wrong_anim);
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
                if(tvAnswer.getText().toString().equals(exercise[1])){
                    // Calculate reaction time
                    timeWhenUserReacted = Float.parseFloat(Timer.getText().toString().substring(3));
                    float reactionTime = timeWhenQuestionShowed - timeWhenUserReacted;
                    totalReactionTime += reactionTime;
                    playSuccess();
                    tvAnswer.setText("");
                    Count.setText( String.valueOf(Integer.parseInt(Count.getText().toString())+1));
                } else {
                    playFailure();
                }
                showRiddle();
                tvAnswer.setText("");
            }
        });
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

        if( time>40 && Integer.parseInt(Count.getText().toString())<5){
            random_num = new Random().nextInt(levelStart.size());
            exercise[0] = levelStart.get(random_num).first;
            exercise[1] = levelStart.get(random_num).second;
            levelStart.remove(random_num);
        }
        else if((time<=40 && time>20 || (Integer.parseInt(Count.getText().toString())>=5)) && Integer.parseInt(Count.getText().toString())<10) {
            random_num = new Random().nextInt(levelMiddle.size());
            exercise[0] = levelMiddle.get(random_num).first;
            exercise[1] = levelMiddle.get(random_num).second;
            levelMiddle.remove(random_num);
        }
        else{
            random_num = new Random().nextInt(levelHigh.size());
            exercise[0] = levelHigh.get(random_num).first;
            exercise[1] = levelHigh.get(random_num).second;
            levelHigh.remove(random_num);
        }

        ++asked;
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        riddle.setText(exercise[0]);
        riddle.startAnimation(tvAnimation);

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
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.exit_fragment);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            FrameLayout mDialogNo = dialog.findViewById(R.id.game);
            mDialogNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            FrameLayout mDialogExit = dialog.findViewById(R.id.exit);
            mDialogExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    MathRiddlesActivity.super.onBackPressed();
                    finish();
                }
            });

            dialog.show();
//        ViewDialogActivity alert = new ViewDialogActivity();
//        alert.showDialog(this);



//        ExitFragment exitFragment = new ExitFragment();
//        exitFragment.show(getSupportFragmentManager(),"bialog");
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.confirm_exit);
//        builder.setIcon(R.drawable.question);
//        builder.setMessage(R.string.you_sure);
//        builder.setPositiveButton(R.string.yes_get_out, (dialog, which) -> MathRiddlesActivity.super.onBackPressed()).setNegativeButton(R.string.stay,null).show(); }
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
}