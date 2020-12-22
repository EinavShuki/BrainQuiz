package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.TabAdapter;
import com.example.brainquiz.fragments.MathScoresFragment;
import com.example.brainquiz.fragments.NumbersMemoryScoresFragment;
import com.example.brainquiz.fragments.SequenceScoreFragment;
import com.example.brainquiz.fragments.VisualMemoryScoreFragment;
import com.google.android.material.tabs.TabLayout;

public class LeaderboardActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MathScoresFragment(), Constants.MATH_TITLE);
        adapter.addFragment(new NumbersMemoryScoresFragment(), Constants.NUMBERS_MEMORY_TITLE);
        adapter.addFragment(new VisualMemoryScoreFragment(), Constants.VISUAL_MEMORY_TITLE);
        adapter.addFragment(new SequenceScoreFragment(), Constants.SEQUENCE_TITLE);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
