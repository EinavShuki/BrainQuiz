package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class NumberMemorySecActivity extends AppCompatActivity {
    SharedPreferences sp;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory_sec);


        number = getIntent().getIntExtra("number", 0);
        sp = getSharedPreferences("numToRemember", MODE_PRIVATE);
        EditText editText = findViewById(R.id.num_input);

        Button submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(v -> {
            if (!editText.getText().toString().equals("")) {
                int num = Integer.parseInt(editText.getText().toString());
                if (number == num)
                    setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("num", number);
        editor.apply();
    }
}