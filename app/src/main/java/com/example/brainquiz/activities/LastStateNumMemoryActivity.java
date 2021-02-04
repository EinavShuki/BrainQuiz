package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.brainquiz.R;

public class LastStateNumMemoryActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences sp;
    RelativeLayout explain;
    Button newGameBtn,lastPointBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_state);

        init();
        explain();
        sp=getSharedPreferences("RememberDetails",MODE_PRIVATE);//got it from NumberMemorySec

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
    private void init(){
        newGameBtn=findViewById(R.id.new_game_btn);
        lastPointBtn=findViewById(R.id.continue_btn);
        explain=findViewById(R.id.popup);
    }
    private void explain() {
        Animation show=AnimationUtils.loadAnimation(LastStateNumMemoryActivity.this,R.anim.popup_anim_show);
        explain.setAnimation(show);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                explain.setVisibility(View.INVISIBLE);
            }
        }, 5000);

    }

}