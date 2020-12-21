package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.brainquiz.R;

import java.util.Random;

public class MathRiddlesActivity extends AppCompatActivity {
    SharedPreferences sp;
    int clickcount=1;
    Random rand = new Random();
    int num1=rand.nextInt(10);
    int num2=rand.nextInt(10);
    int num3=rand.nextInt(10);

    String action1="+";
    String action2="+";
    String[] signs = {"+", "-","*"};
    String[] signs1 = {"+", "-"};
    public void myLevel(){

        if (clickcount <= 5) {
            num1 = rand.nextInt(10)+1;
            num2 = rand.nextInt(10)+1;
            action1 = signs[rand.nextInt(signs.length)];
        }
        else if (clickcount > 5 && clickcount <= 10) {
            num1 = rand.nextInt(90) + 10;
            num2 = rand.nextInt(90) + 10;
            action1 = signs1[rand.nextInt(signs.length)];


        }
        else if (clickcount > 10 && clickcount <= 15) {
            num1 = rand.nextInt(900) + 100;
            num2 = rand.nextInt(900) + 100;
            action1 = signs1[rand.nextInt(signs.length)];

        }
        else if (clickcount > 15 && clickcount <= 20) {
            num1 = rand.nextInt(9000) + 1000;
            num2 = rand.nextInt(900) + 100;
            action1 = signs1[rand.nextInt(signs.length)];

        }
        else if (clickcount > 20 && clickcount <= 25) {
            num1 = rand.nextInt(10)+1;
            num2 = rand.nextInt(10)+1;
            num3 = rand.nextInt(10)+1;
            action1 = signs[rand.nextInt(signs.length)];
            action2 = signs[rand.nextInt(signs.length)];

        }
        else if (clickcount > 25 && clickcount <= 30) {
            num1 = rand.nextInt(10)+1;
            num2 = rand.nextInt(20)+1;
            num3 = rand.nextInt(10)+1;
            action1 = signs[rand.nextInt(signs.length)];
            action2 = signs[rand.nextInt(signs.length)];

        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        clickcount = getIntent().getIntExtra("level", 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles);
        TextView num_riddles = findViewById(R.id.number_riddles);
        EditText ans_riddles = findViewById(R.id.answer_riddles);
        Button finished = findViewById(R.id.finish);


        myLevel();
        String str_num = (num1 + action1 + num2 + "=");
        num_riddles.setText(str_num);
        ans_riddles.setText("");

        finished.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int your_num=Integer.parseInt(String.valueOf(ans_riddles.getText()));
                Intent intent = new Intent(MathRiddlesActivity.this, FailScreenNumberMemoryActivity.class);
                intent.putExtra("wrong number", your_num);
                intent.putExtra("level", clickcount);
                intent.putExtra("screen", "MathRiddlesActivity");


                switch (action1){
                    case "-":
                        if (num1 - num2 != your_num) {
                            intent.putExtra("right number", num1 - num2);
                           startActivity(intent);
                            return; }
                        break;
                    case "+":

                        if (num1 + num2 != Integer.parseInt(String.valueOf(ans_riddles.getText()))) {
                            intent.putExtra("right number", num1 + num2);
                            startActivity(intent);
                            return; }
                        break;

                    case "*":
                        if (num1 * num2 != Integer.parseInt(String.valueOf(ans_riddles.getText()))) {
                            intent.putExtra("right number", num1 * num2);
                            startActivity(intent);
                            return; }
                        break;

                }


                myLevel();
                String str_num = (num1 + action1 + num2 + "=");
                num_riddles.setText(str_num);
                ans_riddles.setText("");
                long start = System.currentTimeMillis();
                clickcount = clickcount + 1;


            }

        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("lev",clickcount);
        editor.apply();
    }
}