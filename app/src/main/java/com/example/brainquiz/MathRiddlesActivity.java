package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


import com.google.android.gms.common.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MathRiddlesActivity extends AppCompatActivity {
    int clickcount=0;
    Random rand = new Random();
    int num1=rand.nextInt(10);
    int num2=rand.nextInt(10);
    String action="+";
    String[] signs = {"+", "-","*"};
    String[] signs1 = {"+", "-"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles);
        TextView num_riddles = findViewById(R.id.number_riddles);
        EditText ans_riddles = findViewById(R.id.answer_riddles);
        Button finished = findViewById(R.id.finish);




        String str_num =(num1 + "+" + num2 + "=" );
        num_riddles.setText(str_num);

        finished.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                switch (action){
                    case "-":
                        if (num1 - num2 != Integer.parseInt(String.valueOf(ans_riddles.getText()))) {
                            return;
                        }
                        break;

                    case "+":
                        if (num1 + num2 != Integer.parseInt(String.valueOf(ans_riddles.getText()))) {
                            return;

                        }
                        break;

                    case "*":
                        if (num1 * num2 != Integer.parseInt(String.valueOf(ans_riddles.getText()))) {
                            return;

                        }
                        break;

                    case "/":
                        if (num1 / num2 != Integer.parseInt(String.valueOf(ans_riddles.getText()))) {
                            return;

                        }
                        break;

                }


                clickcount = clickcount + 1;
                if (clickcount <= 5) {
                     num1 = rand.nextInt(10)+1;
                     num2 = rand.nextInt(10)+1;
                    action = signs[rand.nextInt(signs.length)];
                }
                else if (clickcount > 5 && clickcount <= 10) {
                    num1 = rand.nextInt(90) + 10;
                    num2 = rand.nextInt(90) + 10;
                    action = signs1[rand.nextInt(signs.length)];


                }
                else if (clickcount > 10 && clickcount <= 15) {
                    num1 = rand.nextInt(900) + 100;
                    num2 = rand.nextInt(900) + 100;
                    action = signs1[rand.nextInt(signs.length)];

                }
                else if (clickcount > 15 && clickcount <= 20) {
                    num1 = rand.nextInt(9000) + 1000;
                    num2 = rand.nextInt(900) + 100;
                    action = signs1[rand.nextInt(signs.length)];

                }
                else if (clickcount > 20 && clickcount <= 25) {
                    num1 = rand.nextInt(9000) + 1000;
                    num2 = rand.nextInt(9000) + 1000;
                    action = signs1[rand.nextInt(signs.length)];

                }


                String str_num = (num1 + action + num2 + "=");
                num_riddles.setText(str_num);
                ans_riddles.setText("");


            }
        });
    }
}