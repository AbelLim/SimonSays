package com.example.arx8l.simonsays;

public class UserScore
{
    private String name;
    private int score;

    public UserScore(){}

    public UserScore(String name, int score)
    {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
