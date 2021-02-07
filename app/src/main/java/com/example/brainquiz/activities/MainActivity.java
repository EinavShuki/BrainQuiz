package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    Button btnLeaderboards;
    MaterialCardView numMemory;
    MaterialCardView visualMemoryCard;
    MaterialCardView mathRiddle;
    MaterialCardView colorMatcherCard;

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
    }

    private void initUi() {
        btnLeaderboards = findViewById(R.id.btn_leaderboards);
        numMemory = findViewById(R.id.num_memory);
        visualMemoryCard = findViewById(R.id.visual_memory_card);
        mathRiddle = findViewById(R.id.math_riddle);
        colorMatcherCard = findViewById(R.id.color_matcher_card);
    }


    private void setListeners() {
        btnLeaderboards.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LeaderboardActivity.class)));

        numMemory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LastStateNumMemoryActivity.class)));

        visualMemoryCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VisualMemoryActivity.class);
            intent.putExtra("level", 1);
            intent.putExtra("strike", 1);
            startActivity(intent);
        });
        mathRiddle.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MathRiddlesActivity.class))
        );

        colorMatcherCard.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ColorMatcherActivity.class));
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

}