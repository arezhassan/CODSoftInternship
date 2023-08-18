package com.example.sunshineschoolsystem;

public class FeesModel {
    private String amount;
    private String due;
    private String status;
    private String fine;

    public FeesModel(String amount, String due, String status, String fine) {
        this.amount = amount;
        this.due = due;
        this.status = status;
        this.fine = fine;
    }

    public String getAmount() {
        return amount;
    }

    public String getDue() {
        return due;
    }

    public String getStatus() {
        return status;
    }

    public String getFine() {
        return fine;
    }
}
