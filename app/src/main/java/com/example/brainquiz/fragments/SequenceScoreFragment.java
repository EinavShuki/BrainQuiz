package com.example.brainquiz.fragments;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;

public class SequenceScoreFragment  extends BaseScoreFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.sequence_score_fragment;
    }

    @Override
    protected int getTableId() {
        return R.id.sequence_score_table;
    }

    @Override
    protected String getTableName() {
        return Constants.SEQUENCE_TABLE;
    }
}