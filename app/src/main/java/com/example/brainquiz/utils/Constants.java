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
    public static final char pow_2 = '\u00B2';
    public static final char pow_5 = '\u2075';
    public static final char pow_0 = '\u2070';
    public static final char pow_1 = '\u00B9';
    public static final char pow_9 = '\u2079';
    public static final char pow_3 = '\u00B3';

    public static List<Pair<String, String>> riddlesLevelStart = Arrays.asList(
            new Pair<>("13 - 6 = ", "7"),
            new Pair<>("48 - 9 =", "39"),
            new Pair<>("10 + 5 =" ,"15"),
            new Pair<>("9 / 3 =", "3"),
            new Pair<>("8 X 7 =", "56"),
            new Pair<>("12 X 10 =", "120"),
            new Pair<>("5 X 5 - 5 =", "20"),
            new Pair<>("7 X 4 = ", "28"),
            new Pair<>("7 X 3 =", "21"),
            new Pair<>("9 X 5 =", "45"),
            new Pair<>("2 + 2 X 3 =", "8"),
            new Pair<>("56 - 6 =", "50"),
            new Pair<>("7 - 3 = ", "4"),
            new Pair<>("69 - 4 =", "65"),
            new Pair<>("35 + 11 =", "46")
    );
    public static List<Pair<String, String>> riddlesLevelMiddle = Arrays.asList(
            new Pair<>("8 - 8 / 4 X 3 =", "2"),
            new Pair<>("√(9)", "3"),
            new Pair<>("21,18,15,?", "12"),
            new Pair<>("√(64)", "8"),
            new Pair<>("4, 11, 18, ?", "25")

    );
    public static List<Pair<String, String>> riddlesLevelHigh = Arrays.asList(
            new Pair<>("1" + pow_5 + " + " + "5" + pow_2 + " / "+"25" + pow_0 + " - "+"5" + pow_1, "21"),
            new Pair<>("10 - √(9) X 2 = ", "4"),
            new Pair<>("(3" + pow_2 + " - " + "2" + pow_3 +  ")" + pow_9 + " =", "1"),
            new Pair<>("2,3,5,8,13,?", "21")

    );

}
