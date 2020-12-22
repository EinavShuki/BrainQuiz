package com.example.brainquiz.fragments;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.FirebaseManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseScoreFragment extends Fragment {
    TableLayout tableLayout;
    ProgressBar progressBar;

    protected abstract int getLayoutId();

    protected abstract int getTableId();

    protected abstract String getTableName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tableLayout = view.findViewById(getTableId());
        tableLayout.setVisibility(View.INVISIBLE);
        progressBar = view.findViewById(R.id.progress_bar);
        fetchScores();
        progressBar.setVisibility(View.INVISIBLE);
        tableLayout.setVisibility(View.VISIBLE);
    }

    private void fetchScores() {
        FirebaseManager.getInstance().getScores(getTableName(), scores -> {
            scores.sort((p1, p2) -> Long.compare(p2.second, p1.second));

            int i = 0;
            for (Pair<String, Long> score : scores) {
                View row = getLayoutInflater().inflate(R.layout.table_row, null, false);
                TextView rankCol = row.findViewById(R.id.rank);
                rankCol.setText(String.valueOf(++i));
                TextView nameCol = row.findViewById(R.id.name_col);
                nameCol.setText(score.first);
                TextView scoreCol = row.findViewById(R.id.record);
                scoreCol.setText(score.second.toString());
                tableLayout.addView(row);
            }
        });
    }
}
