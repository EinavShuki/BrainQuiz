package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FailScreenNumberMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen_number_memory);

        int wrongNumber=getIntent().getIntExtra("wrong number",0);
        int rightNumber=getIntent().getIntExtra("right number",0);
        int level=getIntent().getIntExtra("level",1);
        Button tryAgain=findViewById(R.id.try_again_btn);

        TextView wrongNumTv=findViewById(R.id.wrong_num);
        TextView rightNumTv=findViewById(R.id.right_num);
        TextView levelTv=findViewById(R.id.level_tv);

        wrongNumTv.setText(wrongNumber+"");
        rightNumTv.setText(rightNumber+"");
        String lev=getString(R.string.level);
        levelTv.setText(lev+" "+level);

        tryAgain.setOnClickListener(v->{
            Intent intent=new Intent(FailScreenNumberMemoryActivity.this,LastStateNumMemoryActivity.class);
            startActivity(intent);
            finish();
        });

    }
}