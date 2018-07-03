package com.bignerdranch.android.remainderapp;

public class RemainderDataModel {
    String message;
    float date;

    public RemainderDataModel(String message, float date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getDate() {
        return date;
    }

    public void setDate(float date) {
        this.date = date;
    }
}
