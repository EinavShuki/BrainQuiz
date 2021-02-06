package com.example.brainquiz;

public class ColorPair {
    String first, second, third, fourth, query, answer;
    int firstColor, secondColor, thirdColor, fourthColor;

    public ColorPair(String query, String answer, String first, int firstColor, String second, int secondColor, String third, int thirdColor, String fourth, int fourthColor) {
        this.first = first;
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

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
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
