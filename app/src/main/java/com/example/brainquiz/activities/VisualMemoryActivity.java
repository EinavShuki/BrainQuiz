package com.example.brainquiz.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class VisualMemoryActivity extends AppCompatActivity {
    Map<Integer, ArrayList<Integer>> caughtPos = new HashMap<>();
    int strike, numOfBtns;
    LinearLayout mainLayout;
    ImageView imageView;
    MediaPlayer countSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_memory);

        //loading dynamic background
        mainLayout = findViewById(R.id.main_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        imageView = findViewById(R.id.animation_img_view);

        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        mainLayout.setLayoutTransition(layoutTransition);

        int level = getIntent().getIntExtra("level", 1);//from itself or failScreen or Intermediate
        strike = getIntent().getIntExtra("strike", 1);//first from itself,latter from Intermediate or failScreen

        Toolbar toolbar = findViewById(R.id.level_toolbar);
        toolbar.setTitle(toolbar.getTitle() + " " + level);
        if (level == 1)
            explain();
        Levels(level);
    }

    private void explain() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_view, null);
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setAnimationStyle(R.style.popup_window_animation);
        //prevents error
        findViewById(R.id.main_layout).post(new Runnable() {
            public void run() {
                popupWindow.showAtLocation(findViewById(R.id.main_layout), Gravity.TOP, 0, 600);
            }
        });
        popupWindow.update();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (countSound.isPlaying())
                countSound.stop();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void Levels(int level) {
        ArrayList<Button> allBtn = new ArrayList<>();
        if (level == 1)
            numOfBtns = 5;
        else if (level <= 3)
            numOfBtns = 5;
        else
            numOfBtns = level + 2;

        GridLayout btnsLo = findViewById(R.id.grid_lo);
        Button lastPressed = new Button(VisualMemoryActivity.this);
        lastPressed.setText(0 + "");
        Random random = new Random();

        int x = random.nextInt(4);
        int y = random.nextInt(10);
        for (int i = 0; i < numOfBtns; i++) {
            //we don't want duplicates
            while ((caughtPos.containsKey(x) && Objects.requireNonNull(caughtPos.get(x)).contains(y))) {
                x = random.nextInt(4);
                y = random.nextInt(10);
            }
            //insert to the map to avoid duplicates
            if (caughtPos.get(x) == null)
                caughtPos.put(x, new ArrayList<>());
            Objects.requireNonNull(caughtPos.get(x)).add(y);

            Button btn = new Button(VisualMemoryActivity.this);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.setMarginStart(3);
            layoutParams.setMarginEnd(3);
            layoutParams.bottomMargin = 3;
            layoutParams.topMargin = 3;
            layoutParams.setGravity(Gravity.CENTER);
            layoutParams.columnSpec = GridLayout.spec(x);
            layoutParams.rowSpec = GridLayout.spec(y);
            btn.setLayoutParams(layoutParams);
            btn.setBackground(null);
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundResource(R.drawable.visual_memory_num);
            btn.setText(i + 1 + " ");
            if (i > 0)
                allBtn.add(btn);

            btn.setOnClickListener(v -> {
                MediaPlayer popSound = MediaPlayer.create(VisualMemoryActivity.this, R.raw.pop);
                popSound.setVolume(0.3f, 0.3f);
                popSound.start();
                ((Button) v).setVisibility(View.INVISIBLE);
                int currentBtnNum = Integer.parseInt(((Button) v).getText().toString().substring(0, 1));
                int lastPressedBtnNum = Integer.parseInt((lastPressed.getText().toString().substring(0, 1)));

                if (currentBtnNum == lastPressedBtnNum + 1) {
                    lastPressed.setText(currentBtnNum + "");
                    if (currentBtnNum == 1) {
                        if (level != 1) {
                            for (Button b : allBtn) {
                                b.setClickable(false);
                                countSound = MediaPlayer.create(VisualMemoryActivity.this, R.raw.countdown);
                                countSound.start();
                                Animation count = AnimationUtils.loadAnimation(VisualMemoryActivity.this, R.anim.counting);
                                imageView.setAnimation(count);
                                imageView.setVisibility(View.VISIBLE);
                                AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
                                animationDrawable.start();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        countSound.stop();
                                        imageView.setVisibility(View.GONE);
                                        //turn the number like turning cards
                                        b.animate().rotationY(180f).setDuration(1000).start();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b.setBackgroundResource(R.drawable.visual_memory_num_after_1);
                                            }
                                        }, 500);
                                        b.setClickable(true);
                                    }
                                }, 3000);

                            }
                        }
                    }
                    if (currentBtnNum == numOfBtns) {
                        Intent intent;
                        if (level == 30) {
                            intent = new Intent(VisualMemoryActivity.this, WinScreenVisualMemoryActivity.class);
                        } else {
                            intent = new Intent(VisualMemoryActivity.this, VisualMemoryActivity.class);
                            intent.putExtra("level", level + 1);
                        }
                        startActivity(intent);
                        finish();
                    }
                    //currentBtnNum != lastPressedBtnNum + 1
                } else {
                    if (strike < 3) {
                        Intent intent = new Intent(VisualMemoryActivity.this, IntermediateVisualMemoryActivity.class);
                        intent.putExtra("strike", strike);
                        intent.putExtra("numbers", numOfBtns);
                        intent.putExtra("level", level);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(VisualMemoryActivity.this, FailScreenActivity.class);
                        intent.putExtra("level", level);
                        intent.putExtra(Constants.ACTIVITY_NAME_KEY, Constants.VISUAL_MEMORY_TITLE);
                        startActivity(intent);
                    }
                    finish();
                }
            });
            btnsLo.addView(btn);
        }
        caughtPos.clear();
    }

}
