package com.example.brainquiz.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;

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
    int strike, numOfBtns, level;
    LinearLayout mainLayout;
    ImageView imageView, ivLifeOne, ivLifeTwo, ivLifeThree;
    ;
    MediaPlayer countSound, popSound;
    ImageButton zero, one, two, three, four, five, six, seven, eight, nine;
    ArrayList<ImageButton> num_arr = new ArrayList<>();
    Button go;
    boolean vol;
    SharedPreferences volSp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_memory);


        init();
        setListeners();

        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
        mainLayout.setLayoutTransition(layoutTransition);

        Log.i("vol", vol + " is visual");

        level = getIntent().getIntExtra("level", 1);//from itself or failScreen or Intermediate
        strike = getIntent().getIntExtra("strike", 1);//first from itself,latter from Intermediate or failScreen

        setBack();

        Toolbar toolbar = findViewById(R.id.level_toolbar);
        toolbar.setTitle(toolbar.getTitle() + " " + level);
        if (level == 1 && strike == 1)
            explain();
        else if (strike == 1) {
            MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.correct_choice);
            if (vol)
                mp.setVolume(0.3f, 0.3f);
            else
                mp.setVolume(0, 0);
            mp.start();
            go.setVisibility(View.VISIBLE);
        }
        else
            Levels(level);


        if (strike==1) {
            ivLifeThree.setVisibility(View.VISIBLE);
            ivLifeTwo.setVisibility(View.VISIBLE);
            ivLifeOne.setVisibility(View.VISIBLE);
        }
        if (strike==2) {
            ivLifeTwo.setVisibility(View.VISIBLE);
            ivLifeOne.setVisibility(View.VISIBLE);
        }
        if (strike==3)
        {
            ivLifeOne.setVisibility(View.VISIBLE);
        }
    }


    @SuppressLint("ResourceType")
    private void init() {
        mainLayout = findViewById(R.id.main_layout);
        imageView = findViewById(R.id.animation_img_view);
        go = findViewById(R.id.go);

        zero = findViewById(R.id.zero_card);
        zero.setId(1);
        one = findViewById(R.id.one_card);
        one.setId(2);
        two = findViewById(R.id.two_card);
        two.setId(3);
        three = findViewById(R.id.three_card);
        three.setId(4);
        four = findViewById(R.id.four_card);
        four.setId(5);
        five = findViewById(R.id.five_card);
        five.setId(6);
        six = findViewById(R.id.six_card);
        six.setId(7);
        seven = findViewById(R.id.seven_card);
        seven.setId(8);
        eight = findViewById(R.id.eight_card);
        eight.setId(9);
        nine = findViewById(R.id.nine_card);
        nine.setId(10);
        num_arr.add(zero);
        num_arr.add(one);
        num_arr.add(two);
        num_arr.add(three);
        num_arr.add(four);
        num_arr.add(five);
        num_arr.add(six);
        num_arr.add(seven);
        num_arr.add(eight);
        num_arr.add(nine);

        volSp = getSharedPreferences("vol", MODE_PRIVATE);
        vol = volSp.getBoolean("vol", true);

        ivLifeOne = findViewById(R.id.life_one);
        ivLifeTwo = findViewById(R.id.life_two);
        ivLifeThree = findViewById(R.id.life_three);


    }

    private void setBack() {
        switch (level) {
            case 1:
                mainLayout.setBackgroundResource(R.drawable.back);
                break;
            case 2:
                mainLayout.setBackgroundResource(R.drawable.back2);
                break;
            case 6:
                mainLayout.setBackgroundResource(R.drawable.back6);
                break;
            case 3:
                mainLayout.setBackgroundResource(R.drawable.back3);
                break;
            case 4:
            case 9:
                mainLayout.setBackgroundResource(R.drawable.back4);
                break;
            case 5:
                mainLayout.setBackgroundResource(R.drawable.back5);
                break;
            case 7:
                mainLayout.setBackgroundResource(R.drawable.back7);
                break;
            case 8:
                mainLayout.setBackgroundResource(R.drawable.back6);
                break;
            case 10:
                mainLayout.setBackgroundResource(R.drawable.back10);
                break;
        }
    }

    private void explain() {
        for (ImageButton b : num_arr) {
            b.setClickable(false);
        }

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_view, null);
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
//        Animation show = AnimationUtils.loadAnimation(VisualMemoryActivity.this, R.anim.popup_anim_show);
//        popupView.setAnimation(show);
        popupWindow.setAnimationStyle(R.style.popup_window_animation);
        //prevents error
        findViewById(R.id.main_layout).post(new Runnable() {
            public void run() {
                popupWindow.showAtLocation(findViewById(R.id.main_layout), Gravity.TOP, 0, 600);
            }
        });
        popupWindow.update();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 4000);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                go.setVisibility(View.VISIBLE);
                for (ImageButton b : num_arr) {
                    b.setClickable(true);
                }
            }
        });
    }

    private void setListeners() {

        go.setOnClickListener(v -> {
            go.setVisibility(View.GONE);
            Levels(level);
        });

    }

    private void counting(ArrayList<ImageButton> allBtn) {
        for (ImageButton b : allBtn) {
            b.setClickable(false);
            //COUNTING VOLUME
            countSound = MediaPlayer.create(VisualMemoryActivity.this, R.raw.countdown);
            if (vol)
                countSound.setVolume(0.01f, 0.01f);
            else
                countSound.setVolume(0, 0);
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
                            b.setImageResource(R.drawable.behind);
                            b.setBackground(null);
                        }
                    }, 500);
                    b.setClickable(true);
                }
            }, 3000);
        }
    }

    private void Levels(int level) {
        ArrayList<ImageButton> allBtn = new ArrayList<>();
        if (level <= 3)
            numOfBtns = 5;
        else if (level <= 8)
            numOfBtns = level + 2;
        else
            numOfBtns = level;

        GridLayout btnsLo = findViewById(R.id.grid_lo);
        Button lastPressed = new Button(VisualMemoryActivity.this);
        lastPressed.setText(0 + "");
        Random random = new Random();

        int x = random.nextInt(4);
        int y = random.nextInt(5);
        for (int i = 0; i < numOfBtns; i++) {
            //we don't want duplicates
            while ((caughtPos.containsKey(x) && Objects.requireNonNull(caughtPos.get(x)).contains(y))) {
                x = random.nextInt(4);
                y = random.nextInt(5);
            }
            //insert to the map to avoid duplicates
            if (caughtPos.get(x) == null)
                caughtPos.put(x, new ArrayList<>());
            Objects.requireNonNull(caughtPos.get(x)).add(y);

            ImageButton btn = num_arr.get(i);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = 160;
            layoutParams.width = 160;
            layoutParams.bottomMargin = 3;
            layoutParams.topMargin = 3;
            layoutParams.setGravity(Gravity.CENTER);
            layoutParams.columnSpec = GridLayout.spec(x);
            layoutParams.rowSpec = GridLayout.spec(y);
            btn.setLayoutParams(layoutParams);
            btn.setVisibility(View.VISIBLE);

            allBtn.add(btn);

            btn.setOnClickListener(v -> {
                int currentBtnNum = ((ImageButton) v).getId();
                int lastPressedBtnNum = Integer.parseInt((lastPressed.getText().toString().substring(0, 1)));

                //pop sound with pushing on a button
                popSound = MediaPlayer.create(VisualMemoryActivity.this, R.raw.pop);
                if (vol)
                    popSound.setVolume(0.3f, 0.3f);
                else
                    popSound.setVolume(0, 0);
                popSound.start();

                //disappearing animation
                ((ImageButton) v).animate().scaleX(0).scaleY(0).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        ((ImageButton) v).setVisibility(View.INVISIBLE);
                    }
                }).start();

                if (currentBtnNum == lastPressedBtnNum + 1) {
                    lastPressed.setText(currentBtnNum + "");

                    if (currentBtnNum == numOfBtns) {
                        Intent intent;
                        if (level == 10) {
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
                    if (strike == 1) {
                        worngNum(allBtn);
                    } else if (strike == 2) {
                        worngNum(allBtn);
                    } else {
                        Intent intent = new Intent(VisualMemoryActivity.this, FailScreenActivity.class);
                        intent.putExtra("level", level);
                        intent.putExtra(Constants.ACTIVITY_NAME_KEY, Constants.VISUAL_MEMORY_TITLE);
                        startActivity(intent);
                    }
                    finish();

                }
            });
        }
        caughtPos.clear();

        //counting back before disappearing
        counting(allBtn);

    }


    private void worngNum(ArrayList<ImageButton> allBtn) {
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.wrong);
        if (vol)
            mp.setVolume(0.3f, 0.3f);
        else
            mp.setVolume(0, 0);
        mp.start();

        Intent intent=new Intent(VisualMemoryActivity.this,VisualMemoryActivity.class);
        intent.putExtra("strike",strike+1);
        intent.putExtra("level",level);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        backvol.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        backvol.pause();

        Log.i("vol", vol + " onPause visual");
        SharedPreferences.Editor editor = volSp.edit();
        editor.putBoolean("vol", vol);
        editor.apply();
    }

    //    private void Levels(int level) {
//        ArrayList<Button> allBtn = new ArrayList<>();
//        if (level == 1)
//            numOfBtns = 5;
//        else if (level <= 3)
//            numOfBtns = 5;
//        else
//            numOfBtns = level + 2;
//
//        GridLayout btnsLo = findViewById(R.id.grid_lo);
//        Button lastPressed = new Button(VisualMemoryActivity.this);
//        lastPressed.setText(0 + "");
//        Random random = new Random();
//
//        int x = random.nextInt(4);
//        int y = random.nextInt(10);
//        for (int i = 0; i < numOfBtns; i++) {
//            //we don't want duplicates
//            while ((caughtPos.containsKey(x) && Objects.requireNonNull(caughtPos.get(x)).contains(y))) {
//                x = random.nextInt(4);
//                y = random.nextInt(10);
//            }
//            //insert to the map to avoid duplicates
//            if (caughtPos.get(x) == null)
//                caughtPos.put(x, new ArrayList<>());
//            Objects.requireNonNull(caughtPos.get(x)).add(y);
//
//            Button btn = new Button(VisualMemoryActivity.this);
//            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
//            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
//            layoutParams.setMarginStart(3);
//            layoutParams.setMarginEnd(3);
//            layoutParams.bottomMargin = 3;
//            layoutParams.topMargin = 3;
//            layoutParams.setGravity(Gravity.CENTER);
//            layoutParams.columnSpec = GridLayout.spec(x);
//            layoutParams.rowSpec = GridLayout.spec(y);
//            btn.setLayoutParams(layoutParams);
//            btn.setBackground(null);
//            btn.setTextColor(getResources().getColor(R.color.white));
//            btn.setBackgroundResource(R.drawable.visual_memory_num);
//            btn.setText(i + 1 + " ");
//            if (i > 0)
//                allBtn.add(btn);
//
//            btn.setOnClickListener(v -> {
//                MediaPlayer popSound = MediaPlayer.create(VisualMemoryActivity.this, R.raw.pop);
//                popSound.setVolume(0.3f, 0.3f);
//                popSound.start();
//                ((Button) v).setVisibility(View.INVISIBLE);
//                int currentBtnNum = Integer.parseInt(((Button) v).getText().toString().substring(0, 1));
//                int lastPressedBtnNum = Integer.parseInt((lastPressed.getText().toString().substring(0, 1)));
//
//                if (currentBtnNum == lastPressedBtnNum + 1) {
//                    lastPressed.setText(currentBtnNum + "");
//                    if (currentBtnNum == 1) {
//                        if (level != 1) {
//                            for (Button b : allBtn) {
//                                b.setClickable(false);
//                                countSound = MediaPlayer.create(VisualMemoryActivity.this, R.raw.countdown);
//                                countSound.start();
//                                Animation count = AnimationUtils.loadAnimation(VisualMemoryActivity.this, R.anim.counting);
//                                imageView.setAnimation(count);
//                                imageView.setVisibility(View.VISIBLE);
//                                AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//                                animationDrawable.start();
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        countSound.stop();
//                                        imageView.setVisibility(View.GONE);
//                                        //turn the number like turning cards
//                                        b.animate().rotationY(180f).setDuration(1000).start();
//                                        new Handler().postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                b.setBackgroundResource(R.drawable.visual_memory_num_after_1);
//                                            }
//                                        }, 500);
//                                        b.setClickable(true);
//                                    }
//                                }, 3000);
//
//                            }
//                        }
//                    }
//                    if (currentBtnNum == numOfBtns) {
//                        Intent intent;
//                        if (level == 30) {
//                            intent = new Intent(VisualMemoryActivity.this, WinScreenVisualMemoryActivity.class);
//                        } else {
//                            intent = new Intent(VisualMemoryActivity.this, VisualMemoryActivity.class);
//                            intent.putExtra("level", level + 1);
//                        }
//                        startActivity(intent);
//                        finish();
//                    }
//                    //currentBtnNum != lastPressedBtnNum + 1
//                } else {
//                    if (strike < 3) {
//                        Intent intent = new Intent(VisualMemoryActivity.this, IntermediateVisualMemoryActivity.class);
//                        intent.putExtra("strike", strike);
//                        intent.putExtra("numbers", numOfBtns);
//                        intent.putExtra("level", level);
//                        startActivity(intent);
//                    } else {
//                        Intent intent = new Intent(VisualMemoryActivity.this, FailScreenActivity.class);
//                        intent.putExtra("level", level);
//                        intent.putExtra(Constants.ACTIVITY_NAME_KEY, Constants.VISUAL_MEMORY_TITLE);
//                        startActivity(intent);
//                    }
//                    finish();
//                }
//            });
//            btnsLo.addView(btn);
//        }
//        caughtPos.clear();
//    }

}
