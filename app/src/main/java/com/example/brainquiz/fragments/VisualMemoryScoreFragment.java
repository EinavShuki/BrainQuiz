package com.example.brainquiz.fragments;


import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

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
        return Constants.VISUAL_MEMORY_TABLE;
    }

    @Override
    protected int getProgressBarId() {
        return R.id.progress_bar_vis_mem;
    }
}
