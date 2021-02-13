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
    public static final String VISUAL_SCORES_KEY = "visualScoresKey";
    public static final String VISUAL_SCORES_PREFS = "visualScoresPrefs";
    public static final String LEVEL_KEY = "levelKey";
    public static final String NUMBER_KEY = "numberKey";
    public static final String ANSWER_RESULT = "resultKey";
    public static final String ANSWER_KEY = "answerKey";
    public static final String MATH_KEY = "mathKey";
    public static final String MATH_SCORE_KEY = "mathScoreKey";
    public static final String VISUAL_SCORE_KEY = "visualScoreKey";
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

    public static List<Pair<String, String>> riddlesLevel1 = Arrays.asList(
            new Pair<>("13 - 6 = ", "7"),
            new Pair<>("48 - 9 =", "39"),
            new Pair<>("10 + 5 =" ,"15"),
            new Pair<>("9 / 3 =", "3"),
            new Pair<>("14 / 2 =", "7"),
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


    public static List<Pair<String, String>> riddlesLevel2 = Arrays.asList(
            new Pair<>("2" + pow_2 + "=", "4"),
            new Pair<>("3" + pow_2 + "=", "9"),
            new Pair<>("4" + pow_2 + "=", "16"),
            new Pair<>("5" + pow_2 + "=", "25"),
            new Pair<>("6" + pow_2 + "=", "36"),
            new Pair<>("7" + pow_2 + "=", "49"),
            new Pair<>("8" + pow_2 + "=", "64"),
            new Pair<>("9" + pow_2 + "=", "81"),
            new Pair<>("4 * 3.5 =", "14")

    );

    public static List<Pair<String, String>> riddlesLevel3 = Arrays.asList(
            new Pair<>("2 + ? = 6" , "4"),
            new Pair<>("6 + ? = 24" , "18"),
            new Pair<>("9 * ? = 45" , "5"),
            new Pair<>("6 x ? = 30" , "5"),
            new Pair<>("7 x ? = 56" , "8"),
            new Pair<>("9 x ? =72" , "8"),
            new Pair<>("8 x ? = 48" , "6"),
            new Pair<>("4 x ? = 12" , "3"),
            new Pair<>("3 x ? = 21" , "21"),
            new Pair<>("4 x ? = 12" , "3")
    );


    public static List<Pair<String, String>> riddlesLevel4 = Arrays.asList(
            new Pair<>("√(10+6) =", "4"),
            new Pair<>("√(81) =", "9"),
            new Pair<>("√(25) =", "5"),
            new Pair<>("√(36) =", "6"),
            new Pair<>("√(49) =", "7"),
            new Pair<>("√(4+5) =", "3"),
            new Pair<>("√(64) =", "8"),
            new Pair<>("84 / 12 =", "7")

    );

    public static List<Pair<String, String>> riddlesLevel5 = Arrays.asList(
            new Pair<>("8 - 8 / 4 X 3 =", "2"),
            new Pair<>("84 / 12 =", "7"),
            new Pair<>("60 / 4 =", "15"),
            new Pair<>("2,3,5,8,13,?", "21")
    );

    public static List<Pair<String, String>> riddlesLevel6 = Arrays.asList(
            new Pair<>("1" + pow_5 + " + " + "5" + pow_2 + " / "+"25" + pow_0 + " - "+"5" + pow_1, "21"),
            new Pair<>("10 - √(9) X 2 = ", "4"),
            new Pair<>("8 - 8 / 4 X 3 =", "2"),
            new Pair<>("(3" + pow_2 + " - " + "2" + pow_3 +  ")" + pow_9 + " =", "1")


    );

    public static List<ColorPair> colorPairs = Arrays.asList(
            new ColorPair(R.string.text, R.string.green,"one",R.string.green, R.color.blue, R.string.yellow, R.color.orange, R.string.black, R.color.green,R.string.orange, R.color.yellow),
            new ColorPair(R.string.text, R.string.green,"two",R.string.blue, R.color.green, R.string.green, R.color.blue, R.string.black, R.color.orange,R.string.yellow, R.color.red),
            new ColorPair(R.string.text, R.string.green,"three",R.string.blue, R.color.blue, R.string.black, R.color.green, R.string.green, R.color.orange,R.string.orange, R.color.red),
            new ColorPair(R.string.text, R.string.green,"four",R.string.blue, R.color.green, R.string.black, R.color.blue, R.string.yellow, R.color.orange,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.green,"one",R.string.blue, R.color.green, R.string.black, R.color.blue, R.string.yellow, R.color.orange,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.green,"two",R.string.blue, R.color.blue, R.string.black, R.color.green, R.string.yellow, R.color.orange,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.green,"three",R.string.blue, R.color.blue, R.string.black, R.color.purple, R.string.yellow, R.color.green,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.green,"four",R.string.blue, R.color.blue, R.string.green, R.color.purple, R.string.yellow, R.color.red,R.string.red, R.color.green),

            new ColorPair(R.string.text, R.string.blue,"one",R.string.blue, R.color.yellow, R.string.yellow, R.color.orange, R.string.black, R.color.blue,R.string.orange, R.color.red),
            new ColorPair(R.string.text, R.string.blue,"two",R.string.green, R.color.blue, R.string.blue, R.color.green, R.string.black, R.color.orange,R.string.yellow, R.color.red),
            new ColorPair(R.string.text, R.string.blue,"three",R.string.yellow, R.color.blue, R.string.black, R.color.green, R.string.blue, R.color.orange,R.string.orange, R.color.red),
            new ColorPair(R.string.text, R.string.blue,"four",R.string.green, R.color.green, R.string.black, R.color.blue, R.string.yellow, R.color.orange,R.string.blue, R.color.red),
            new ColorPair(R.string.color, R.string.blue,"one",R.string.red, R.color.blue, R.string.blue, R.color.red, R.string.yellow, R.color.orange,R.string.green, R.color.yellow),
            new ColorPair(R.string.color, R.string.blue,"two",R.string.blue, R.color.green, R.string.red, R.color.blue, R.string.yellow, R.color.orange,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.blue,"three",R.string.blue, R.color.green, R.string.red, R.color.purple, R.string.yellow, R.color.blue,R.string.green, R.color.yellow),
            new ColorPair(R.string.color, R.string.blue,"four",R.string.blue, R.color.black, R.string.black, R.color.purple, R.string.yellow, R.color.red,R.string.red, R.color.blue),

            new ColorPair(R.string.text, R.string.red,"one",R.string.red, R.color.yellow, R.string.yellow, R.color.orange, R.string.black, R.color.green,R.string.orange, R.color.red),
            new ColorPair(R.string.text, R.string.red,"two",R.string.green, R.color.blue, R.string.red, R.color.yellow, R.string.black, R.color.orange,R.string.yellow, R.color.red),
            new ColorPair(R.string.text, R.string.red,"three",R.string.yellow, R.color.blue, R.string.black, R.color.green, R.string.red, R.color.orange,R.string.orange, R.color.red),
            new ColorPair(R.string.text, R.string.red,"four",R.string.green, R.color.green, R.string.black, R.color.blue, R.string.yellow, R.color.orange,R.string.red, R.color.yellow),
            new ColorPair(R.string.color, R.string.red,"one",R.string.blue, R.color.red, R.string.black, R.color.green, R.string.red, R.color.orange,R.string.green, R.color.yellow),
            new ColorPair(R.string.color, R.string.red,"two",R.string.red, R.color.green, R.string.blue, R.color.red, R.string.yellow, R.color.orange,R.string.green, R.color.blue),
            new ColorPair(R.string.color, R.string.red,"three",R.string.blue, R.color.green, R.string.red, R.color.purple, R.string.yellow, R.color.red,R.string.green, R.color.yellow),
            new ColorPair(R.string.color, R.string.red,"four",R.string.red, R.color.black, R.string.black, R.color.purple, R.string.yellow, R.color.yellow,R.string.blue, R.color.red),

            new ColorPair(R.string.text, R.string.yellow,"one",R.string.yellow, R.color.red, R.string.blue, R.color.orange, R.string.black, R.color.green,R.string.orange, R.color.blue),
            new ColorPair(R.string.text, R.string.yellow,"two",R.string.green, R.color.blue, R.string.yellow, R.color.red, R.string.orange, R.color.orange,R.string.red, R.color.green),
            new ColorPair(R.string.text, R.string.yellow,"three",R.string.red, R.color.blue, R.string.black, R.color.green, R.string.yellow, R.color.orange,R.string.orange, R.color.yellow),
            new ColorPair(R.string.text, R.string.yellow,"four",R.string.green, R.color.yellow, R.string.black, R.color.green, R.string.red, R.color.orange,R.string.yellow, R.color.blue),
            new ColorPair(R.string.color, R.string.yellow,"one",R.string.blue, R.color.yellow, R.string.black, R.color.green, R.string.yellow, R.color.orange,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.yellow,"two",R.string.yellow, R.color.green, R.string.blue, R.color.yellow, R.string.orange, R.color.orange,R.string.green, R.color.blue),
            new ColorPair(R.string.color, R.string.yellow,"three",R.string.blue, R.color.green, R.string.red, R.color.purple, R.string.orange, R.color.yellow,R.string.yellow, R.color.orange),
            new ColorPair(R.string.color, R.string.yellow,"four",R.string.red, R.color.black, R.string.black, R.color.purple, R.string.yellow, R.color.red,R.string.blue, R.color.yellow),

            new ColorPair(R.string.text, R.string.orange,"one",R.string.orange, R.color.red, R.string.blue, R.color.orange, R.string.black, R.color.green,R.string.red, R.color.blue),
            new ColorPair(R.string.text, R.string.orange,"two",R.string.green, R.color.blue, R.string.orange, R.color.red, R.string.yellow, R.color.orange,R.string.red, R.color.green),
            new ColorPair(R.string.text, R.string.orange,"three",R.string.blue, R.color.red, R.string.black, R.color.green, R.string.orange, R.color.yellow,R.string.red, R.color.orange),
            new ColorPair(R.string.text, R.string.orange,"four",R.string.green, R.color.yellow, R.string.black, R.color.green, R.string.red, R.color.orange,R.string.orange, R.color.blue),
            new ColorPair(R.string.color, R.string.orange,"one",R.string.yellow, R.color.orange, R.string.black, R.color.green, R.string.red, R.color.yellow,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.orange,"two",R.string.yellow, R.color.green, R.string.blue, R.color.orange, R.string.orange, R.color.red,R.string.green, R.color.blue),
            new ColorPair(R.string.color, R.string.orange,"three",R.string.blue, R.color.green, R.string.red, R.color.purple, R.string.orange, R.color.orange,R.string.yellow, R.color.red),
            new ColorPair(R.string.color, R.string.orange,"four",R.string.red, R.color.black, R.string.orange, R.color.purple, R.string.yellow, R.color.red,R.string.blue, R.color.orange),

            new ColorPair(R.string.text, R.string.purple,"one",R.string.purple, R.color.blue, R.string.blue, R.color.orange, R.string.black, R.color.green,R.string.red, R.color.yellow),
            new ColorPair(R.string.text, R.string.purple,"two",R.string.green, R.color.blue, R.string.purple, R.color.red, R.string.yellow, R.color.purple,R.string.red, R.color.green),
            new ColorPair(R.string.text, R.string.purple,"three",R.string.blue, R.color.red, R.string.black, R.color.purple, R.string.purple, R.color.yellow,R.string.red, R.color.orange),
            new ColorPair(R.string.text, R.string.purple,"four",R.string.green, R.color.yellow, R.string.black, R.color.purple, R.string.red, R.color.orange,R.string.purple, R.color.blue),
            new ColorPair(R.string.color, R.string.purple,"one",R.string.yellow, R.color.purple, R.string.purple, R.color.green, R.string.red, R.color.yellow,R.string.green, R.color.red),
            new ColorPair(R.string.color, R.string.purple,"two",R.string.yellow, R.color.green, R.string.blue, R.color.purple, R.string.purple, R.color.red,R.string.green, R.color.blue),
            new ColorPair(R.string.color, R.string.purple,"three",R.string.red, R.color.green, R.string.purple, R.color.black, R.string.orange, R.color.purple,R.string.yellow, R.color.red),
            new ColorPair(R.string.color, R.string.purple,"four",R.string.purple, R.color.black, R.string.orange, R.color.yellow, R.string.yellow, R.color.red,R.string.blue, R.color.purple),

            new ColorPair(R.string.text, R.string.blue,"one",R.string.blue, R.color.purple, R.string.yellow, R.color.orange, R.string.black, R.color.green,R.string.red, R.color.yellow),
            new ColorPair(R.string.text, R.string.red,"one",R.string.red, R.color.orange, R.string.blue, R.color.purple, R.string.black, R.color.green,R.string.orange, R.color.yellow)

    );

}
