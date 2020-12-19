package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

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
        adapter.addFragment(new MathScoresFragment(), "Math");
        adapter.addFragment(new NumbersMemoryScoresFragment(), "Numbers Memory");
        adapter.addFragment(new VisualMemoryScoreFragment(), "Visual Memory");
        adapter.addFragment(new SequenceScoreFragment(), "Sequence");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
