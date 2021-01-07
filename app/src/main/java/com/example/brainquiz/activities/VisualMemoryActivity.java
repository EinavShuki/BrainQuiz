package com.example.brainquiz.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_memory);

        mainLayout = findViewById(R.id.main_layout);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        mainLayout.setLayoutTransition(layoutTransition);

        int level = getIntent().getIntExtra("level", 1);//from itself or failScreen or Intermediate
        strike = getIntent().getIntExtra("strike", 1);//first from itself,latter from Intermediate or failScreen

        Toolbar toolbar = findViewById(R.id.level_toolbar);
        toolbar.setTitle(toolbar.getTitle() + " " + level);
        ImageButton questionMarkBtn = findViewById(R.id.questionmark_btn);
        if(level==1)
            animatQuestion(questionMarkBtn);
        questionMarkBtn.setOnClickListener(v -> explain(v));

        Levels(level);
    }

    private void animatQuestion(View v){
        v.animate().setStartDelay(1000).scaleX(2.3f).scaleY(2.3f).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                v.animate().setStartDelay(100).scaleX(1f).scaleY(1f).setDuration(1000);
            }
        }).start();
    }
    private void explain(View v) {
        v.setPressed(true);
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_view, null);
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setAnimationStyle(R.style.popup_window_animation);
        popupWindow.showAtLocation(v, Gravity.TOP, 0, 300);
        popupWindow.update();
    }

    private void Levels(int level) {
        ArrayList<Button> allBtn = new ArrayList<>();
        if (level <= 4)
            numOfBtns = 5;
        else
            numOfBtns = level;

        GridLayout btnsLo = findViewById(R.id.grid_lo);
        Button lastPressed = new Button(VisualMemoryActivity.this);
        lastPressed.setText(0 + "");
        Random random = new Random();

        int x = random.nextInt(3);
        int y = random.nextInt(10);
        for (int i = 0; i < numOfBtns; i++) {
            //we don't want duplicates
            while ((caughtPos.containsKey(x) && Objects.requireNonNull(caughtPos.get(x)).contains(y))) {
                x = random.nextInt(3);
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
                ((Button) v).setVisibility(View.INVISIBLE);
                int currentBtnNum = Integer.parseInt(((Button) v).getText().toString().substring(0, 1));
                int lastPressedBtnNum = Integer.parseInt((lastPressed.getText().toString().substring(0, 1)));

                if (currentBtnNum == lastPressedBtnNum + 1) {
                    lastPressed.setText(currentBtnNum + "");
                    if (currentBtnNum == 1) {
                        if (level != 1) {
                            for (Button b : allBtn) {
                                b.setBackgroundResource(R.drawable.visual_memory_num_after_1);
                                ;
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
