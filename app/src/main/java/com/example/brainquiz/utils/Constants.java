package com.example.brainquiz.utils;

import android.util.Pair;

import com.example.brainquiz.ColorPair;
import com.example.brainquiz.R;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String MATH_TITLE = "Math";
    public static final String NUMBERS_MEMORY_TITLE = "Numbers Memory";
    public static final String VISUAL_MEMORY_TITLE = "Visual Memory";
    public static final String COLOR_MATCH_TITLE = "Color Match";
    public static final String MATH_TABLE = "mathScores";
    public static final String NUMBERS_MEMORY_TABLE = "numberMemoryScore";
    public static final String VISUAL_MEMORY_TABLE = "visualMemoryScore";
    public static final String COLOR_MATCH_TABLE = "colorMatchScore";
    public static final String SCREEN_KEY = "screenKey";
    public static final String MATH_SCORES_KEY = "mathScoresKey";
    public static final String MATH_SCORES_PREFS = "mathScoresPrefs";
    public static final String VISUAL_SCORES_KEY = "mathScoresKey";
    public static final String VISUAL_SCORES_PREFS = "mathScoresPrefs";
    public static final String LEVEL_KEY = "levelKey";
    public static final String NUMBER_KEY = "numberKey";
    public static final String ANSWER_RESULT = "resultKey";
    public static final String ANSWER_KEY = "answerKey";
    public static final String MATH_KEY = "mathKey";
    public static final String MATH_SCORE_KEY = "mathScoreKey";
    public static final String ACTIVITY_NAME_KEY = "nameActivity";
    public static final String ACCURACY_KEY = "accuracy";
    public static final String REACTION_TIME_KEY = "reactionTime";
    public static final String NUMBERS_MEMORY_KEY = "numberMemoryKey";
    public static final String VISUAL_MEMORY_KEY = "visualMemoryKey";
    public static final String COLOR_MATCH_KEY = "colorMatchKey";
    public static final String SCORE_KEY = "SCORE";
    public static final String LONGEST_RUN_KEY = "longestRunKey";
    public static final String CORRECT_KEY = "correctKey";
    public static final String TABLE_KEY = "TABLE";
    public static final String USERNAME_PREFS = "UserName";
    public static final String DIALOG_SAVE_SCORE = "save score dialog";
    public static final char pow_2 = '\u00B2';
    public static final char pow_5 = '\u2075';
    public static final char pow_0 = '\u2070';
    public static final char pow_1 = '\u00B9';
    public static final char pow_9 = '\u2079';
    public static final char pow_3 = '\u00B3';
    public static final int EASY_DURATION = 10000;
    public static final int INTERMEDIATE_DURATION = 9000;
    public static final int MEDIUM_DURATION = 8000;
    public static final int HIGH_DURATION = 6000;

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

    public static List<ColorPair> colorPairs = Arrays.asList(
            new ColorPair("Text Green","one","Green", R.color.blue, "Yellow", R.color.orange, "Black", R.color.green,"Orange", R.color.yellow),
            new ColorPair("Text Green","two","Blue", R.color.green, "Green", R.color.blue, "Black", R.color.orange,"Yellow", R.color.red),
            new ColorPair("Text Green","three","Blue", R.color.blue, "Black", R.color.green, "Green", R.color.orange,"Orange", R.color.red),
            new ColorPair("Text Green","four","Blue", R.color.green, "Black", R.color.blue, "Yellow", R.color.orange,"Green", R.color.red),
            new ColorPair("Color Green","one","Blue", R.color.green, "Black", R.color.blue, "Yellow", R.color.orange,"Green", R.color.red),
            new ColorPair("Color Green","two","Blue", R.color.blue, "Black", R.color.green, "Yellow", R.color.orange,"Green", R.color.red),
            new ColorPair("Color Green","three","Blue", R.color.blue, "Black", R.color.purple, "Yellow", R.color.green,"Green", R.color.red),
            new ColorPair("Color Green","four","Blue", R.color.blue, "Green", R.color.purple, "Yellow", R.color.red,"Red", R.color.green),

            new ColorPair("Text Blue","one","Blue", R.color.yellow, "Yellow", R.color.orange, "Black", R.color.blue,"Orange", R.color.red),
            new ColorPair("Text Blue","two","Green", R.color.blue, "Blue", R.color.green, "Black", R.color.orange,"Yellow", R.color.red),
            new ColorPair("Text Blue","three","Yellow", R.color.blue, "Black", R.color.green, "Blue", R.color.orange,"Orange", R.color.red),
            new ColorPair("Text Blue","four","Green", R.color.green, "Black", R.color.blue, "Yellow", R.color.orange,"Blue", R.color.red),
            new ColorPair("Color Blue","one","Red", R.color.blue, "Blue", R.color.red, "Yellow", R.color.orange,"Green", R.color.yellow),
            new ColorPair("Color Blue","two","Blue", R.color.green, "Red", R.color.blue, "Yellow", R.color.orange,"Green", R.color.red),
            new ColorPair("Color Blue","three","Blue", R.color.green, "Red", R.color.purple, "Yellow", R.color.blue,"Green", R.color.yellow),
            new ColorPair("Color Blue","four","Blue", R.color.black, "Black", R.color.purple, "Yellow", R.color.red,"Red", R.color.blue),

            new ColorPair("Text Red","one","Red", R.color.yellow, "Yellow", R.color.orange, "Black", R.color.green,"Orange", R.color.red),
            new ColorPair("Text Red","two","Green", R.color.blue, "Red", R.color.yellow, "Black", R.color.orange,"Yellow", R.color.red),
            new ColorPair("Text Red","three","Yellow", R.color.blue, "Black", R.color.green, "Red", R.color.orange,"Orange", R.color.red),
            new ColorPair("Text Red","four","Green", R.color.green, "Black", R.color.blue, "Yellow", R.color.orange,"Red", R.color.yellow),
            new ColorPair("Color Red","one","Blue", R.color.red, "Black", R.color.green, "Red", R.color.orange,"Green", R.color.yellow),
            new ColorPair("Color Red","two","Red", R.color.green, "Blue", R.color.red, "Yellow", R.color.orange,"Green", R.color.blue),
            new ColorPair("Color Red","three","Blue", R.color.green, "Red", R.color.purple, "Yellow", R.color.red,"Green", R.color.yellow),
            new ColorPair("Color Red","four","Red", R.color.black, "Black", R.color.purple, "Yellow", R.color.yellow,"Blue", R.color.red),

            new ColorPair("Text Yellow","one","Yellow", R.color.red, "Blue", R.color.orange, "Black", R.color.green,"Orange", R.color.blue),
            new ColorPair("Text Yellow","two","Green", R.color.blue, "Yellow", R.color.red, "Orange", R.color.orange,"Red", R.color.green),
            new ColorPair("Text Yellow","three","Red", R.color.blue, "Black", R.color.green, "Yellow", R.color.orange,"Orange", R.color.yellow),
            new ColorPair("Text Yellow","four","Green", R.color.yellow, "Black", R.color.green, "Red", R.color.orange,"Yellow", R.color.blue),
            new ColorPair("Color Yellow","one","Blue", R.color.yellow, "Black", R.color.green, "Yellow", R.color.orange,"Green", R.color.red),
            new ColorPair("Color Yellow","two","Yellow", R.color.green, "Blue", R.color.yellow, "Orange", R.color.orange,"Green", R.color.blue),
            new ColorPair("Color Yellow","three","Blue", R.color.green, "Red", R.color.purple, "Orange", R.color.yellow,"Yellow", R.color.orange),
            new ColorPair("Color Yellow","four","Red", R.color.black, "Black", R.color.purple, "Yellow", R.color.red,"Blue", R.color.yellow),

            new ColorPair("Text Orange","one","Orange", R.color.red, "Blue", R.color.orange, "Black", R.color.green,"Red", R.color.blue),
            new ColorPair("Text Orange","two","Green", R.color.blue, "Orange", R.color.red, "Yellow", R.color.orange,"Red", R.color.green),
            new ColorPair("Text Orange","three","Blue", R.color.red, "Black", R.color.green, "Orange", R.color.yellow,"Red", R.color.orange),
            new ColorPair("Text Orange","four","Green", R.color.yellow, "Black", R.color.green, "Red", R.color.orange,"Orange", R.color.blue),
            new ColorPair("Color Orange","one","Yellow", R.color.orange, "Black", R.color.green, "Red", R.color.yellow,"Green", R.color.red),
            new ColorPair("Color Orange","two","Yellow", R.color.green, "Blue", R.color.orange, "Orange", R.color.red,"Green", R.color.blue),
            new ColorPair("Color Orange","three","Blue", R.color.green, "Red", R.color.purple, "Orange", R.color.orange,"Yellow", R.color.red),
            new ColorPair("Color Orange","four","Red", R.color.black, "Orange", R.color.purple, "Yellow", R.color.red,"Blue", R.color.orange),

            new ColorPair("Text Purple","one","Purple", R.color.blue, "Blue", R.color.orange, "Black", R.color.green,"Red", R.color.yellow),
            new ColorPair("Text Purple","two","Green", R.color.blue, "Purple", R.color.red, "Yellow", R.color.purple,"Red", R.color.green),
            new ColorPair("Text Purple","three","Blue", R.color.red, "Black", R.color.purple, "Purple", R.color.yellow,"Red", R.color.orange),
            new ColorPair("Text Purple","four","Green", R.color.yellow, "Black", R.color.purple, "Red", R.color.orange,"Purple", R.color.blue),
            new ColorPair("Color Purple","one","Yellow", R.color.purple, "Purple", R.color.green, "Red", R.color.yellow,"Green", R.color.red),
            new ColorPair("Color Purple","two","Yellow", R.color.green, "Blue", R.color.purple, "Purple", R.color.red,"Green", R.color.blue),
            new ColorPair("Color Purple","three","Red", R.color.green, "Purple", R.color.black, "Orange", R.color.purple,"Yellow", R.color.red),
            new ColorPair("Color Purple","four","Purple", R.color.black, "Orange", R.color.yellow, "Yellow", R.color.red,"Blue", R.color.purple),

            new ColorPair("Text Blue","one","Blue", R.color.purple, "Yellow", R.color.orange, "Black", R.color.green,"Red", R.color.yellow),
            new ColorPair("Text Red","one","Red", R.color.orange, "Blue", R.color.purple, "Black", R.color.green,"Orange", R.color.yellow)

    );

}
