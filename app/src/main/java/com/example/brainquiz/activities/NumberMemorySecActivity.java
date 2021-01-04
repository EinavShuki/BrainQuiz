package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.brainquiz.R;

public class NumberMemorySecActivity extends AppCompatActivity {
    SharedPreferences sp;
    int number, lev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory_sec);


        lev = getIntent().getIntExtra("level", 1);
        number = getIntent().getIntExtra("number", 5);

        sp = getSharedPreferences("RememberDetails", MODE_PRIVATE);
        EditText editText = findViewById(R.id.num_input);

        Button submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(v -> {
            if (!editText.getText().toString().equals("") && TextUtils.isDigitsOnly(editText.getText())) {
                //the number from the user
                int num = Integer.parseInt(editText.getText().toString());
                if (number == num) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Intent intent = new Intent(NumberMemorySecActivity.this, FailScreenActivity.class);
                    intent.putExtra("wrong number", num);
                    intent.putExtra("right number", number);
                    intent.putExtra("level", lev);
                    intent.putExtra("nameActivity","NumberMemorySecActivity");
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("num", number);
        editor.putInt("lev", lev);
        editor.apply();
    }

}