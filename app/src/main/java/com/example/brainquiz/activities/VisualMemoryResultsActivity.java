package com.example.brainquiz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.brainquiz.R;
import com.example.brainquiz.fragments.SaveScoreDialog;
import com.example.brainquiz.utils.Constants;
import com.example.brainquiz.utils.SharedPrefsManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Collections;

public class VisualMemoryResultsActivity extends AppCompatActivity {

    LineChart lineChart;
    TextView tvAccuracy, tvReactionTime, tvScore;
    Button btnTryAgain, btnSaveScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_memory_results);

        MediaPlayer sound=MediaPlayer.create(this,R.raw.dramatic);
        sound.start();

//        tvAccuracy = findViewById(R.id.tv_longest_seq);
        tvScore = findViewById(R.id.tv_visual_score);
//        tvReactionTime = findViewById(R.id.tv_reaction);
        btnTryAgain = findViewById(R.id.try_again_visual_btn);
        btnSaveScore = findViewById(R.id.save_visual_score_btn);

//        String accuracy =  getIntent().getStringExtra(Constants.ACCURACY_KEY) + " %";
//        String reaction = getIntent().getStringExtra(Constants.REACTION_TIME_KEY) + "sec";
        int score = getIntent().getIntExtra(Constants.VISUAL_SCORE_KEY, 0);

        tvScore.setText(String.valueOf(score));
//        tvAccuracy.setText(accuracy);
//        tvReactionTime.setText(reaction);

        btnSaveScore.setOnClickListener(v -> {
            SaveScoreDialog saveScoreDialog = new SaveScoreDialog();
            Bundle args = new Bundle();
            args.putInt(Constants.SCORE_KEY, score);
            args.putString(Constants.SCREEN_KEY, Constants.VISUAL_MEMORY_TABLE);
            saveScoreDialog.setArguments(args);
            saveScoreDialog.show(getSupportFragmentManager(), Constants.DIALOG_SAVE_SCORE);
        });

        btnTryAgain.setOnClickListener(v -> {
            Intent intent = new Intent(VisualMemoryResultsActivity.this, VisualMemoryActivity.class);
            startActivity(intent);
            finish();
        });


        lineChart = findViewById(R.id.line_chart_visual);

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

        ArrayList<Integer> lastScores = SharedPrefsManager.readLastScores(VisualMemoryResultsActivity.this, Constants.VISUAL_SCORES_KEY, Constants.VISUAL_SCORES_PREFS);
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