package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;

import org.w3c.dom.Text;

public class FailScreenVisualMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_screen_visual_memory);
        TextView textView=findViewById(R.id.record_tv);
        int level=getIntent().getIntExtra("level",1);
        textView.setText(level+"");
        Button tryAgainBtn=findViewById(R.id.try_again_btn);
        tryAgainBtn.setOnClickListener(v->{
            Intent intent=new Intent(FailScreenVisualMemoryActivity.this,VisualMemoryActivity.class);
            intent.putExtra("level",1);
            intent.putExtra("strike",1);
            startActivity(intent);
            finish();
        });
    }
}