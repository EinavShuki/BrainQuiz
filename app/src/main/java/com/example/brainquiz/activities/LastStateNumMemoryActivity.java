package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.brainquiz.R;

public class LastStateNumMemoryActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_state);

        sp=getSharedPreferences("RememberDetails",MODE_PRIVATE);//got it from NumberMemorySec

        Button newGameBtn=findViewById(R.id.new_game_btn);
        Button lastPointBtn=findViewById(R.id.continue_btn);

        intent=new Intent(LastStateNumMemoryActivity.this,NumberMemoryActivity.class);

        if(sp.contains("num"))//got it from NumberMemorySecActivity
            lastPointBtn.setEnabled(true);

        newGameBtn.setOnClickListener(v->{
            intent.putExtra("num",0);
            intent.putExtra("lev",0);
            startActivity(intent);
        });
        lastPointBtn.setOnClickListener(v->{
            intent.putExtra("num",sp.getInt("num",5));
            intent.putExtra("lev",sp.getInt("lev",1));
            startActivity(intent);
        });

    }
}