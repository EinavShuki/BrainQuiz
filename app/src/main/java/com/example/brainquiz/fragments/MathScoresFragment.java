package com.example.brainquiz.fragments;

import com.example.brainquiz.R;

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
        return "mathScores";
    }

}