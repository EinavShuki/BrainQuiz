package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.brainquiz.ColorPair;
import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorMatcherActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvFirst, tvSecond, tvThird, tvFourth, tvScore, tvTimer, textTimer;
    MaterialCardView cvFirstCard, cvSecondCard, cvThirdCard, cvFourthCard;
    LottieAnimationView correctAnimView;
    LottieAnimationView wrongAnimView;
    List<ColorPair> colorPairs = new ArrayList<>(Constants.colorPairs);
    int random_num = new Random().nextInt(colorPairs.size());
    ColorPair colorPair = colorPairs.get(random_num);
    CountDownTimer countDownTimer;
    ProgressBar barTimer;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matcher);

        initUi();
        setListeners();
        showCards();
        startTimer();
//        new CountDownTimer(31000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                tvTimer.setText(String.format("%02d:%02d", millisUntilFinished / 1000 / 60, millisUntilFinished / 1000 % 60));
//                if (millisUntilFinished / 1000 % 60 == 10) {
//                    tvTimer.setTextColor(getColor(R.color.red));
//                }
//                time = millisUntilFinished / 1000 % 60;
//            }
//
//            public void onFinish() {
//                tvTimer.setText("Done!");
//            }
//        }.start();
    }

    private void initUi() {
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
        barTimer = findViewById(R.id.bar_timer);
    }

    private void setListeners() {
    }

    private void startTimer(){
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(barTimer, "progress", 100, 0);
        progressAnimator.setDuration(10000);
        progressAnimator.start();
        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }

    private void showCards() {
        // Get color pair to show
        random_num = new Random().nextInt(colorPairs.size());
        colorPair = colorPairs.get(random_num);
        colorPairs.remove(random_num);

        Animation firstAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.left_to_right);
        Animation secondAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.right_to_left);

        // Show second
        cvSecondCard.startAnimation(secondAnimation);
        tvSecond.setText(colorPair.getActualText());

        // Show first
        cvFirstCard.startAnimation(firstAnimation);
        tvFirst.setText(colorPair.getMeaningText());

        // Show third
        cvThirdCard.startAnimation(secondAnimation);
        tvThird.setText(colorPair.getActualText());

        // Show first
        cvFourthCard.startAnimation(firstAnimation);
        tvFourth.setText(colorPair.getMeaningText());
    }

    private void playCorrect() {
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
    }

    @Override
    public void onClick(View view) {
        Object tag = view.getTag();
        if ("yes".equals(tag)) {
            if (colorPair.isCorrect()) {
                playCorrect();
            } else {
                playWrong();
            }

        } else if ("no".equals(tag)) {
            if (!colorPair.isCorrect()) {
                playCorrect();
            } else {
                playWrong();
            }

        }
        showCards();
    }
}