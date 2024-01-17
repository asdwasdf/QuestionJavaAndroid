package com.example.myquizappjava.model;

public class HighScore {
    private int id;
    private String name;
    private int Score;

    public HighScore(int id, String name, int score) {
        this.id = id;
        this.name = name;
        Score = score;
    }
    public HighScore() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return Score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        Score = score;
    }

}
