package com.example.brainquiz.fragments;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

public class MathScoresFragment extends BaseScoreFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.math_score_fragment;
    }

    @Override
    protected int getTableId() {
        return R.id.math_score_table;
    }

    @Override
    protected String getTableName() {
        return Constants.MATH_TABLE;
    }

    @Override
    protected int getProgressBarId() {
        return R.id.progress_bar_math;
    }

}