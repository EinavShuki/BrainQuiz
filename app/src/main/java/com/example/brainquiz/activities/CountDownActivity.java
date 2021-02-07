package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.brainquiz.R;

public class CountDownActivity extends AppCompatActivity {
    Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        handler.postDelayed(() -> {
            /* Create an Intent that will start the Menu-Activity. */
            Intent mainIntent = new Intent(CountDownActivity.this, ColorMatcherActivity.class);
            startActivity(mainIntent);
            finish();
        }, 3000);
    }


}