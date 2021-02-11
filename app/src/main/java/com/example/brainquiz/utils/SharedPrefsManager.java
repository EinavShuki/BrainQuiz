package com.example.brainquiz.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SharedPrefsManager {
    public static void saveInLastScores(String score, Context context,String gameKey,String prefsName) {
        String lastScores = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
                .getString(gameKey, "");
        SharedPreferences.Editor editor = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE).edit();
        StringBuilder newScores = new StringBuilder();

        // There is a saved scores
        if (lastScores != null) {

            StringTokenizer st = new StringTokenizer(lastScores, ",");

            if (st.countTokens() == 3) {

                ArrayList<Integer> scores = new ArrayList<>();

                while (st.hasMoreTokens()) {
                    scores.add(Integer.parseInt(st.nextToken()));
                }

                // The new score is bigger then the max
                if (Integer.parseInt(score) > Collections.max(scores)) {
                    // Delete the minimum score
                    int min = Collections.min(scores);
                    for (Integer i : scores) {
                        if (i != min) {
                            newScores.append(i).append(",");
                        }
                    }
                    newScores.append(score).append(",");
                    editor.putString(gameKey, newScores.toString());
                    editor.apply();
                }
            }

            if (st.countTokens() < 3) {
                lastScores += score + ",";
                editor.putString(gameKey, lastScores);
                editor.apply();
            }
        }
    }

    public static ArrayList<Integer> readLastScores(Context context,String gameKey,String prefsName) {
        String lastScores = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
                .getString(gameKey, "");
        StringTokenizer st = new StringTokenizer(lastScores, ",");

        ArrayList<Integer> scores = new ArrayList<>();

        while (st.hasMoreTokens()) {
            scores.add(Integer.parseInt(st.nextToken()));
        }

        return scores;
    }
}
