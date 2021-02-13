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



    public int getQueryColor() {
        return queryColor;
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






    public String getAnswer() {
        return answer;
    }


    public int getFirstColor() {
        return firstColor;
    }



    public int getSecondColor() {
        return secondColor;
    }



    public int getThirdColor() {
        return thirdColor;
    }



    public int getFourthColor() {
        return fourthColor;
    }


}
