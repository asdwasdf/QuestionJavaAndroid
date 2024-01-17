package com.example.myquizappjava.model;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
public class Questions {

    private int id;
    private String question;
    private byte[] img;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    private int correctAnswer;

    public Questions(int id, String question, byte[] img, String optionOne, String optionTwo,
                     String optionThree, String optionFour, int correctAnswer) {
        this.id = id;
        this.question = question;
        this.img = img;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAnswer = correctAnswer;
    }

    public Questions() {

    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public byte[] getImg() {
        return img;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setImage(byte[] image) {
        this.img = image;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setId(int id) {
        this.id = id;
    }

    }
