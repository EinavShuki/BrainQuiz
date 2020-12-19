package com.example.brainquiz;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brainquiz.utils.FirebaseManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SequenceScoreFragment  extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sequence_score_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchScores();
    }

    private void fetchScores() {
        FirebaseManager.getInstance().getScores("sequenceScore", scores -> {
            for (Pair<String, Long> score : scores){
                Log.i("sequenceScores:", score.first + score.second);
            }
        });
    }
}