package com.example.brainquiz.utils;

import android.util.Pair;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String MATH_TITLE = "Math";
    public static final String NUMBERS_MEMORY_TITLE = "Numbers Memory";
    public static final String VISUAL_MEMORY_TITLE = "Visual Memory";
    public static final String SEQUENCE_TITLE = "Sequence";
    public static final String MATH_TABLE = "mathScores";
    public static final String NUMBERS_MEMORY_TABLE = "numberMemoryScore";
    public static final String VISUAL_MEMORY_TABLE = "visualMemoryScore";
    public static final String SEQUENCE_TABLE = "sequenceScore";
    public static final String MATH_KEY = "mathKey";
    public static final String NUMBERS_MEMORY_KEY = "numberMemoryKey";
    public static final String VISUAL_MEMORY_KEY = "visualMemoryKey";
    public static final String SEQUENCE_KEY = "sequenceKey";
    public static final String SCORE_KEY = "SCORE";
    public static final String TABLE_KEY = "TABLE";
    public static final String USERNAME_PREFS = "UserName";
    public static final String DIALOG_SAVE_SCORE = "save score dialog";

    public static List<Pair<String, String>> riddlesLevelStart = Arrays.asList(
            new Pair<>("5+2", "7"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4")
    );
    public static List<Pair<String, String>> riddlesLevelMiddle = Arrays.asList(
            new Pair<>("5+2", "7"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4")
    );
    public static List<Pair<String, String>> riddlesLevelHigh = Arrays.asList(
            new Pair<>("5+2", "7"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4"),
            new Pair<>("3", "4")
    );
}
