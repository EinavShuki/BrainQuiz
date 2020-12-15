package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class LastStateActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_state);

        sp=getSharedPreferences("numToRemember",MODE_PRIVATE);
        Button newGameBtn=findViewById(R.id.new_game_btn);
        Button lastPointBtn=findViewById(R.id.continue_btn);
        intent=new Intent(LastStateActivity.this,NumberMemoryActivity.class);
        if(sp.contains("num"))//got it from NumberMemorySecActivity
            lastPointBtn.setEnabled(true);

        newGameBtn.setOnClickListener(v->{
            intent.putExtra("num",0);
            startActivity(intent);
        });
        lastPointBtn.setOnClickListener(v->{
            intent.putExtra("num",sp.getInt("num",5));
            startActivity(intent);
        });

    }
}