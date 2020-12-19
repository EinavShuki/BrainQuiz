package com.example.brainquiz.fragments;


import com.example.brainquiz.R;

public class VisualMemoryScoreFragment extends BaseScoreFragment{
    @Override
    protected int getLayoutId() {
        return R.layout.visual_memory_score_fragment;
    }

    @Override
    protected int getTableId() {
        return R.id.visual_memory_score_table;
    }

    @Override
    protected String getTableName() {
        return "visualMemoryScore";
    }
}
