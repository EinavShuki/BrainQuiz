package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.brainquiz.R;
import com.example.brainquiz.WinScreenVisualMemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class VisualMemoryActivity extends AppCompatActivity {
    Map<Integer, ArrayList<Integer>> caughtPos = new HashMap<>();
    int strike, numOfBtns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_memory);
        int level = getIntent().getIntExtra("level", 1);//from itself or failScreen or Intermediate
        strike = getIntent().getIntExtra("strike", 1);//first from itself,latter from Intermediate or failScreen
        Levels(level);
    }


    private void Levels(int level) {
        ArrayList<Button> allBtn = new ArrayList<>();
        if (level < 4)
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
            if(caughtPos.get(x)==null)
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
                                b.setBackgroundResource(R.drawable.visual_memory_num_after_1);;
                            }
                        }
                    }
                    if (currentBtnNum == numOfBtns) {
                        Intent intent;
                        if(level==30) {
                            intent = new Intent(VisualMemoryActivity.this, WinScreenVisualMemory.class);
                        }
                        else {
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
                        Intent intent = new Intent(VisualMemoryActivity.this, FailScreenVisualMemoryActivity.class);
                        intent.putExtra("level", level);
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
