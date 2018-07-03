package com.bignerdranch.android.remainderapp;

public class RemainderDataModel {
    String message;
    String date;


    public RemainderDataModel(String date, String message) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
