package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.SharedPrefsManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MathRiddlesResultsActivity extends AppCompatActivity {

    LineChart lineChart;
    TextView tvAccuracy;
    TextView tvReactionTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_riddles_results);

        tvAccuracy = findViewById(R.id.tv_accuracy);
        tvReactionTime = findViewById(R.id.tv_reaction);

        String accuracy =  getIntent().getStringExtra(Constants.ACCURACY_KEY) + " %";
        String reaction = getIntent().getStringExtra(Constants.REACTION_TIME_KEY) + "sec";

        tvAccuracy.setText(accuracy);
        tvReactionTime.setText(reaction);

        lineChart = findViewById(R.id.line_chart);

        lineChart.getXAxis().setDrawAxisLine(false);

        //remove left border
        lineChart.getAxisLeft().setDrawAxisLine(false);

        //remove right border
        lineChart.getAxisRight().setDrawAxisLine(false);

        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawLimitLinesBehindData(false);
        lineChart.getAxisLeft().setDrawLabels(false);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getXAxis().setDrawLabels(false);
        lineChart.getXAxis().setDrawLimitLinesBehindData(false);
        lineChart.getLegend().setEnabled(false);

        ArrayList<Integer> lastScores = SharedPrefsManager.readLastScores(MathRiddlesResultsActivity.this);
        Collections.sort(lastScores);

        ArrayList<Entry> yValues = new ArrayList<>();

        for(int i = 0; i < lastScores.size(); i++){
            yValues.add(new Entry(i + 1, lastScores.get(i)));
        }


        LineDataSet set1 = new LineDataSet(yValues, "");
        set1.setFillColor(Color.RED);
        set1.setColor(getColor(R.color.blue_500));
        set1.setCircleColor(getColor(R.color.blue_500));
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setValueTextSize(14);
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        data.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + ((int) value);
            }
        });

        lineChart.setData(data);
    }
}