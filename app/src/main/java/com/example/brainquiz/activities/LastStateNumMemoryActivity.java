package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

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
            intent.putExtra("number",5);
            intent.putExtra("level",1);
            startActivity(intent);
            finish();
        });
        lastPointBtn.setOnClickListener(v->{
            intent.putExtra("number",sp.getInt("num",5));
            intent.putExtra("level",sp.getInt("lev",1));
            startActivity(intent);
            finish();
        });

    }

}