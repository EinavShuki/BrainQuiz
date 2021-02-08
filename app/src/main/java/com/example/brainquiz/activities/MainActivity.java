package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.fragments.PagerModel;
import com.example.brainquiz.fragments.ScoresPagerAdapter;
import com.example.brainquiz.utils.Constants;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLeaderboards;
    MaterialCardView numMemory;
    MaterialCardView visualMemoryCard;
    MaterialCardView mathRiddle;
    MaterialCardView colorMatcherCard;
    ImageButton volume;
    boolean vol=true ;
    MediaPlayer backvol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MediaPlayer backMusic = MediaPlayer.create(MainActivity.this, R.raw.background_visual_memory);
//        backMusic.setVolume(0.1f, 0.1f);
//        backMusic.setLooping(true);
//        backMusic.start();

        initUi();
        setListeners();
        backvol.setVolume(0.05f, 0.05f);
        backvol.start();
    }


    private void initUi() {
        btnLeaderboards = findViewById(R.id.btn_leaderboards);
        numMemory = findViewById(R.id.num_memory);
        visualMemoryCard = findViewById(R.id.visual_memory_card);
        mathRiddle = findViewById(R.id.math_riddle);
        colorMatcherCard = findViewById(R.id.color_matcher_card);
        volume = findViewById(R.id.volume_btn);
        backvol = MediaPlayer.create(MainActivity.this, R.raw.background_visual_memory);
    }


    private void setListeners() {
        btnLeaderboards.setOnClickListener(view -> {
            backvol.stop();
//            startActivity(new Intent(MainActivity.this, LeaderboardActivity.class));
            showLeaderboardsDialog();
        });

        numMemory.setOnClickListener(v -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, LastStateNumMemoryActivity.class));
        });

        visualMemoryCard.setOnClickListener(v -> {
            backvol.pause();
            Intent intent = new Intent(MainActivity.this, VisualMemoryActivity.class);
            startActivity(intent);
        });
        mathRiddle.setOnClickListener(v -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, MathRiddlesActivity.class));
        });

        colorMatcherCard.setOnClickListener(v -> {
            backvol.stop();
            startActivity(new Intent(MainActivity.this, ColorMatcherActivity.class));
        });

        volume.setOnClickListener(v -> {
            if (vol) {
                volume.setImageResource(R.drawable.volume_off);
                backvol.setVolume(0.05f, 0.05f);
            } else {
                volume.setImageResource(R.drawable.volume_up);
                backvol.setVolume(0, 0);
            }
            vol = !vol;
            backvol.start();
        });

    }
    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_fragment);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


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
                MainActivity.super.onBackPressed();
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
    protected void onResume() {
        super.onResume();
        Log.i("vol",vol+" ");
        if (vol) {
            volume.setImageResource(R.drawable.volume_up);
            backvol.setVolume(0.05f, 0.05f);
        } else {
            volume.setImageResource(R.drawable.volume_off);
            backvol.setVolume(0, 0);
        }
        backvol.start();
    }

    private void showLeaderboardsDialog(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);

        List<PagerModel> pagerList = new ArrayList<>();
        pagerList.add(new PagerModel("1", "Math Scores", Constants.MATH_TABLE));
        pagerList.add(new PagerModel("2", "Color Match Scores", Constants.COLOR_MATCH_TABLE));
        pagerList.add(new PagerModel("3", "Visual Memory", Constants.VISUAL_MEMORY_TABLE));
        pagerList.add(new PagerModel("4", "Numbers Memory", Constants.NUMBERS_MEMORY_TABLE));

        ScoresPagerAdapter pagerAdapter = new ScoresPagerAdapter(this, pagerList);

        ViewPager pager = dialog.findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backvol.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        backvol.pause();
    }
}