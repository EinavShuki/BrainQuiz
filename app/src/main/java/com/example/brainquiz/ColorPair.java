package com.example.brainquiz;

public class ColorPair {
    String query, answer;
    int firstColor, secondColor, thirdColor, fourthColor, queryText, queryColor, first, second, third, fourth;

    public ColorPair(int queryText, int queryColor, String answer, int first, int firstColor, int second, int secondColor, int third, int thirdColor, int fourth, int fourthColor) {
        this.first = first;
        this.queryText = queryText;
        this.queryColor = queryColor;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.query = query;
        this.answer = answer;
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        this.thirdColor = thirdColor;
        this.fourthColor = fourthColor;
    }

    public int getQueryText() {
        return queryText;
    }

    public void setQueryText(int queryText) {
        this.queryText = queryText;
    }

    public int getQueryColor() {
        return queryColor;
    }

    public void setQueryColor(int queryColor) {
        this.queryColor = queryColor;
    }

    public int getFirst() {
        return first;
    }


    public int getSecond() {
        return second;
    }


    public int getThird() {
        return third;
    }


    public int getFourth() {
        return fourth;
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getFirstColor() {
        return firstColor;
    }

    public void setFirstColor(int firstColor) {
        this.firstColor = firstColor;
    }

    public int getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(int secondColor) {
        this.secondColor = secondColor;
    }

    public int getThirdColor() {
        return thirdColor;
    }

    public void setThirdColor(int thirdColor) {
        this.thirdColor = thirdColor;
    }

    public int getFourthColor() {
        return fourthColor;
    }

    public void setFourthColor(int fourthColor) {
        this.fourthColor = fourthColor;
    }
}
