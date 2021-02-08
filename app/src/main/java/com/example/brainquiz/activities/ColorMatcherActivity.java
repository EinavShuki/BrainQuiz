package com.example.brainquiz.activities;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.brainquiz.ColorPair;
import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorMatcherActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvFirst, tvSecond, tvThird, tvFourth, tvScore, tvGameOver, textTimer;
    CardView cvFirstCard, cvSecondCard, cvThirdCard, cvFourthCard;
    ImageView ivLifeOne, ivLifeTwo, ivLifeThree;
    LinearLayout rootLayout;
    LottieAnimationView correctAnimView, wrongAnimView, countdownAnimView;
    LottieAnimationView levelUpAnimView;
    ObjectAnimator progressAnimator;
    List<ColorPair> colorPairs = new ArrayList<>(Constants.colorPairs);
    int random_num = new Random().nextInt(colorPairs.size());
    String answer;
    ColorPair colorPair = colorPairs.get(random_num);
    ProgressBar barTimer;
    int life = 3, longestRun = 0, run = 0, scoreLevel = 1, correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matcher);
        initUi();
        setListeners();
        countdownAnimView = findViewById(R.id.countdown_anim);
        new Handler().postDelayed(() -> {
            rootLayout.setVisibility(View.VISIBLE);
            showCards();
        }, 3000);
    }

    private void initUi() {
        rootLayout = findViewById(R.id.root_layout);
        rootLayout.setVisibility(View.GONE);
        tvFirst = findViewById(R.id.tv_first);
        tvSecond = findViewById(R.id.tv_second);
        tvThird = findViewById(R.id.tv_third);
        tvFourth = findViewById(R.id.tv_fourth);
        cvFirstCard = findViewById(R.id.first_card);
        cvSecondCard = findViewById(R.id.second_card);
        cvThirdCard = findViewById(R.id.third_card);
        cvFourthCard = findViewById(R.id.fourth_card);
        textTimer = findViewById(R.id.textTimer);
        correctAnimView = findViewById(R.id.correct_answer_anim);
        wrongAnimView = findViewById(R.id.wrong_answer_anim);
        levelUpAnimView = findViewById(R.id.level_up_anim);
        barTimer = findViewById(R.id.bar_timer);
        ivLifeOne = findViewById(R.id.life_one);
        ivLifeTwo = findViewById(R.id.life_two);
        ivLifeThree = findViewById(R.id.life_three);
        tvScore = findViewById(R.id.tv_colors_score);
        tvGameOver = findViewById(R.id.tv_game_over);
    }

    private void setListeners() {
        cvFirstCard.setOnClickListener(this);
        cvSecondCard.setOnClickListener(this);
        cvThirdCard.setOnClickListener(this);
        cvFourthCard.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (progressAnimator != null){
            progressAnimator.removeAllListeners();
        }
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        if (progressAnimator != null){
            progressAnimator.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (progressAnimator != null){
            progressAnimator.removeAllListeners();
        }
        super.onDestroy();
    }


    private void showCards() {
        // Get color pair to show
        if(colorPairs.size() == 0){
            colorPairs = new ArrayList<>(Constants.colorPairs);
        }
        random_num = new Random().nextInt(colorPairs.size());
        colorPair = colorPairs.get(random_num);
        colorPairs.remove(random_num);

        answer = colorPair.getAnswer();

        Animation firstAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.left_to_right);
        Animation secondAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.right_to_left);


        // Show first
        cvFirstCard.setCardBackgroundColor(getColor(colorPair.getFirstColor()));

        cvFirstCard.startAnimation(secondAnimation);
        tvFirst.setText(colorPair.getFirst());

        // Show second
        cvSecondCard.setCardBackgroundColor(getColor(colorPair.getSecondColor()));
        cvSecondCard.startAnimation(secondAnimation);
        tvSecond.setText(colorPair.getSecond());


        // Show third
        cvThirdCard.setCardBackgroundColor(getColor(colorPair.getThirdColor()));
        cvThirdCard.startAnimation(firstAnimation);
        tvThird.setText(colorPair.getThird());

        // Show first
        cvFourthCard.setCardBackgroundColor(getColor(colorPair.getFourthColor()));
        cvFourthCard.startAnimation(firstAnimation);
        tvFourth.setText(colorPair.getFourth());

        textTimer.setText(colorPair.getQuery());

        progressAnimator = ObjectAnimator.ofInt(barTimer, "progress", 100, 0);

        int score = Integer.parseInt(tvScore.getText().toString());
        if(score <= 4){
            progressAnimator.setDuration(Constants.EASY_DURATION);
        } else if(score >= 5 && score <= 24){
            scoreLevel = 2;
            progressAnimator.setDuration(Constants.INTERMEDIATE_DURATION);
        } else if(score >= 25 && score <= 59){
            scoreLevel = 5;
            progressAnimator.setDuration(Constants.MEDIUM_DURATION);
        } else if (score >= 60){
            scoreLevel = 10;
            progressAnimator.setDuration(Constants.HIGH_DURATION);
        }

        progressAnimator.start();
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!animation.isPaused()){
                    playWrong();
                    showCards();
                }
            }
        });
    }

    private void playCorrect() {
        correct++;
        int score = Integer.parseInt(tvScore.getText().toString());
        if(score == 5 || score == 24 || score == 60){
            playLevelUp();
            addScore();
            return;
        }

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

        addScore();
        showCards();
    }

    private void playWrong() {
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
        if (life == 3){
            life--;
            run = 0;
            ivLifeThree.setVisibility(View.GONE);
            progressAnimator.pause();
            showCards();
        } else if(life == 2){
            life--;
            run = 0;
            ivLifeTwo.setVisibility(View.GONE);
            progressAnimator.pause();
            showCards();
        } else if(life == 1) {
            ivLifeOne.setVisibility(View.GONE);
            gameOver();
            return;
        }

    }

    private void addScore(){
        ++run;
        if(run > longestRun){
            longestRun = run;
        }
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        tvScore.setText(String.valueOf(Integer.parseInt(tvScore.getText().toString())+ scoreLevel));
        tvScore.startAnimation(tvAnimation);
    }

    private void gameOver(){
        progressAnimator.pause();
        cvFirstCard.setVisibility(View.GONE);
        cvSecondCard.setVisibility(View.GONE);
        cvThirdCard.setVisibility(View.GONE);
        cvFourthCard.setVisibility(View.GONE);
        textTimer.setVisibility(View.GONE);
        barTimer.setVisibility(View.GONE);
        tvScore.setVisibility(View.GONE);
        Animation tvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.popup_anim_show);
        tvGameOver.startAnimation(tvAnimation);
        tvAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvGameOver.setVisibility(View.GONE);
                Intent intent = new Intent(ColorMatcherActivity.this, ColorMatcherResultsActivity.class);
                intent.putExtra(Constants.LONGEST_RUN_KEY, longestRun);
                intent.putExtra(Constants.SCORE_KEY, tvScore.getText().toString());
                intent.putExtra(Constants.CORRECT_KEY, correct);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvGameOver.setVisibility(View.VISIBLE);
    }

    private void playLevelUp(){
        levelUpAnimView.setVisibility(View.VISIBLE);
        levelUpAnimView.playAnimation();
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.correct_choice);
        mp.start();
        levelUpAnimView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                levelUpAnimView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                levelUpAnimView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        showCards();
    }

    @Override
    public void onClick(View view) {
        progressAnimator.pause();
        Object tag = view.getTag();

        if ("one".equals(tag)) {
            if(!answer.equals("one")){
                playWrong();
            } else {
                playCorrect();
            }

        } else if ("two".equals(tag)) {
            if(!answer.equals("two")){
                playWrong();
            } else {
                playCorrect();
            }
        } else if ("three".equals(tag)) {
            if(!answer.equals("three")){
                playWrong();
            } else {
                playCorrect();
            }
        } else if ("four".equals(tag)) {
            if(!answer.equals("four")){
                playWrong();
            } else {
                playCorrect();
            }
        }
    }


}