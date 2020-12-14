package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NumberMemorySecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory_sec);

        int number=getIntent().getIntExtra("number",0);
        EditText editText=findViewById(R.id.num_input);
        Button submitBtn=findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(v->{
            int num=Integer.parseInt(editText.getText().toString());
            if(number==num)
                setResult(RESULT_OK);
            finish();
        });
    }
}