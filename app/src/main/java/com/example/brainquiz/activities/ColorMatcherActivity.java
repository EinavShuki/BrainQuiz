package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.ColorPair;
import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorMatcherActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvMeaning, tvActual;
    Button btnYes, btnNo;
    MaterialCardView cvFirstCard, cvSecondCard;
    List<ColorPair> colorPairs = new ArrayList<>(Constants.colorPairs);
    int random_num = new Random().nextInt(colorPairs.size());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matcher);

        initUi();
        setListeners();
        showCards();
    }

    private void initUi(){
        tvMeaning = findViewById(R.id.tv_meaning);
        tvActual = findViewById(R.id.tv_actual);
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        cvFirstCard = findViewById(R.id.first_card);
        cvSecondCard = findViewById(R.id.second_card);
    }

    private void setListeners(){
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }

    private void showCards(){
        // Get color pair to show
        random_num = new Random().nextInt(colorPairs.size());
        ColorPair colorPair = colorPairs.get(random_num);
        colorPairs.remove(random_num);

        Animation cvAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        // Show second
        cvSecondCard.startAnimation(cvAnimation);
        tvActual.setText(colorPair.getActualText());
        tvActual.setTextColor(getColor(colorPair.getActualColor()));

        // Show first
        cvFirstCard.startAnimation(cvAnimation);
        tvMeaning.setText(colorPair.getMeaningText());
        tvMeaning.setTextColor(getColor(colorPair.getMeaningColor()));
    }

    @Override
    public void onClick(View view) {

    }
}