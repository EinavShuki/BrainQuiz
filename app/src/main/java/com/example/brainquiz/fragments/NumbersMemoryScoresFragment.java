package com.example.brainquiz.fragments;


import com.example.brainquiz.R;

public class NumbersMemoryScoresFragment extends BaseScoreFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.number_memory_score_fragment;
    }

    @Override
    protected int getTableId() {
        return R.id.numbers_memory_score_table;
    }

    @Override
    protected String getTableName() {
        return "numberMemoryScore";
    }

}