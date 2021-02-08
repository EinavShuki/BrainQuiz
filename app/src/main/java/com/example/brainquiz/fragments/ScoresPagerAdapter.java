package com.example.brainquiz.fragments;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.FirebaseManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ScoresPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    Context context;
    LayoutInflater inflater;
    List<PagerModel> pagerList;

    public ScoresPagerAdapter(Context context, List<PagerModel> pagerList) {
        this.context = context;
        this.pagerList = pagerList;
        inflater = ((Activity) context).getLayoutInflater();
    }


    @Override
    public int getCount() {
        return pagerList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.pager_list_item, container, false);

        PagerModel model = pagerList.get(position);

        TableLayout tableLayout = view.findViewById(R.id.score_table);

        FirebaseManager.getInstance().getScores(model.getTable(), scores -> {
            scores.sort((p1, p2) -> Long.compare(p2.second, p1.second));

            int i = 0;
            for (Pair<String, Long> score : scores) {
                View row = inflater.inflate(R.layout.table_row, null, false);
                TextView rankCol = row.findViewById(R.id.rank);
                rankCol.setText(String.valueOf(++i));
                TextView nameCol = row.findViewById(R.id.name_col);
                nameCol.setText(score.first);
                TextView scoreCol = row.findViewById(R.id.record);
                scoreCol.setText(score.second.toString());
                tableLayout.addView(row);
            }
        });

        view.setTag(position);
        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
