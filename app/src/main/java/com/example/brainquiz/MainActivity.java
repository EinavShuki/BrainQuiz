package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCardView numMemory=findViewById(R.id.num_memory);
        numMemory.setOnClickListener(v->{

            Intent intent=new Intent(MainActivity.this,NumberMemoryActivity.class);
            startActivity(intent);
        });

    }
}