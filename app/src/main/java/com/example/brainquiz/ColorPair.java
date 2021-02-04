package com.example.brainquiz;

public class ColorPair {
    String meaningText, actualText;
    int meaningColor, actualColor;

    public ColorPair(String meaningText, int meaningColor,String actualText, int actualColor) {
        this.meaningText = meaningText;
        this.actualText = actualText;
        this.meaningColor = meaningColor;
        this.actualColor = actualColor;
    }

    public String getMeaningText() {
        return meaningText;
    }

    public void setMeaningText(String meaningText) {
        this.meaningText = meaningText;
    }

    public String getActualText() {
        return actualText;
    }

    public void setActualText(String actualText) {
        this.actualText = actualText;
    }

    public int getMeaningColor() {
        return meaningColor;
    }

    public void setMeaningColor(int meaningColor) {
        this.meaningColor = meaningColor;
    }

    public int getActualColor() {
        return actualColor;
    }

    public void setActualColor(int actualColor) {
        this.actualColor = actualColor;
    }
}
