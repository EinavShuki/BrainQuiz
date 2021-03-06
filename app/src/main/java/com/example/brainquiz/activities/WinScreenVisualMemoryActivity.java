package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

import com.example.brainquiz.R;
import com.example.brainquiz.fragments.SaveScoreDialog;
import com.example.brainquiz.utils.Constants;

public class WinScreenVisualMemoryActivity extends AppCompatActivity {

    Button homeBtn, saveBtn;
    ImageView f1,f2,f3,f4,f5;
    ObjectAnimator animator5A, animator5B, animator1A, animator1B, animator2A, animator2B, animator3A, animator3B, animator4A, animator4B;
    AnimatorSet set1,set2,set3,set4,set5;
    String name_activity, table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen_visual_memory);

        MediaPlayer applause = MediaPlayer.create(this, R.raw.applause);
        applause.start();
        //HERE YOU GET THE ACTIVITY YOU WIN FROM
        name_activity = getIntent().getStringExtra(Constants.ACTIVITY_NAME_KEY);
        int score = getIntent().getIntExtra(Constants.VISUAL_SCORE_KEY, 0);
        if (name_activity.equals(Constants.VISUAL_MEMORY_TITLE)){
            table = Constants.VISUAL_MEMORY_TABLE;
        } else {
            table = Constants.NUMBERS_MEMORY_TABLE;
        }
        init();

        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(WinScreenVisualMemoryActivity.this, MainActivity.class);
            applause.stop();
            startActivity(intent);
            finish();
        });
        saveBtn.setOnClickListener(v->{
            SaveScoreDialog saveScoreDialog = new SaveScoreDialog();
            Bundle args = new Bundle();
            args.putInt(Constants.SCORE_KEY, score);
            args.putString(Constants.SCREEN_KEY, table);
            saveScoreDialog.setArguments(args);
            saveScoreDialog.show(getSupportFragmentManager(), Constants.DIALOG_SAVE_SCORE);
        });

  animations();

    }
    private void animations()
    {
        animator5A = ObjectAnimator.ofFloat(f5, "scaleY", 2).setDuration(2500);
        animator5A.setRepeatMode(ValueAnimator.RESTART);
        animator5A.setRepeatCount(4);
        animator5B = ObjectAnimator.ofFloat(f5, "scaleX", 2).setDuration(2500);
        animator5B.setRepeatMode(ValueAnimator.RESTART);
        animator5B.setRepeatCount(4);
        set5 = new AnimatorSet();
        set5.playTogether(animator5A, animator5B);
        set5.start();

        animator1A = ObjectAnimator.ofFloat(f1, "scaleY", 2).setDuration(2000);
        animator1A.setRepeatMode(ValueAnimator.RESTART);
        animator1A.setRepeatCount(4);
        animator1B = ObjectAnimator.ofFloat(f1, "scaleX", 2).setDuration(2000);
        animator1B.setRepeatMode(ValueAnimator.RESTART);
        animator1B.setRepeatCount(4);
        set1 = new AnimatorSet();
        set1.playTogether(animator1A, animator1B);
        set1.start();

        animator2A = ObjectAnimator.ofFloat(f2, "scaleY", 2).setDuration(1100);
        animator2A.setRepeatMode(ValueAnimator.RESTART);
        animator2A.setRepeatCount(4);
        animator2B = ObjectAnimator.ofFloat(f2, "scaleX", 2).setDuration(1100);
        animator2B.setRepeatMode(ValueAnimator.RESTART);
        animator2B.setRepeatCount(4);
        set2 = new AnimatorSet();
        set2.playTogether(animator2A, animator2B);
        set2.start();

        animator3A = ObjectAnimator.ofFloat(f3, "scaleY", 2).setDuration(1700);
        animator3A.setRepeatMode(ValueAnimator.RESTART);
        animator3A.setRepeatCount(4);
        animator3B = ObjectAnimator.ofFloat(f3, "scaleX", 2).setDuration(1700);
        animator3B.setRepeatMode(ValueAnimator.RESTART);
        animator3B.setRepeatCount(4);
        set3 = new AnimatorSet();
        set3.playTogether(animator3A, animator3B);
        set3.start();

        animator4A = ObjectAnimator.ofFloat(f4, "scaleY", 2).setDuration(2100);
        animator4A.setRepeatMode(ValueAnimator.RESTART);
        animator4A.setRepeatCount(4);
        animator4B = ObjectAnimator.ofFloat(f4, "scaleX", 2).setDuration(2100);
        animator4B.setRepeatMode(ValueAnimator.RESTART);
        animator4B.setRepeatCount(4);
        set4 = new AnimatorSet();
        set4.playTogether(animator4A, animator4B);
        set4.start();


    }

    private void init() {
        homeBtn = findViewById(R.id.home_btn);
        saveBtn = findViewById(R.id.save_score_btn);
        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
    }
}