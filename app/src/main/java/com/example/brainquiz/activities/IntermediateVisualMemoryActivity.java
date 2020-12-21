package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;

public class IntermediateVisualMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_visual_memory);

        int strike=getIntent().getIntExtra("strike",1);
        int numbers=getIntent().getIntExtra("numbers",1);
        int level=getIntent().getIntExtra("level",1);

        TextView strikesTv=findViewById(R.id.strikes_tv);
        TextView numbersTv=findViewById(R.id.numbers_tv);
        numbersTv.setText(numbers+"");
        strikesTv.setText(strike+" of 3");

        Button continueBtn=findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(v->{
            Intent intent=new Intent(IntermediateVisualMemoryActivity.this,VisualMemoryActivity.class);
            intent.putExtra("strike",strike+1);
            intent.putExtra("level",level);
            startActivity(intent);
            finish();
        });
    }
}