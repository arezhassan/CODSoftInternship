package com.example.sunshineschoolsystem;

public class GradesModel {
    String date;
    String TotalMarks;
    String ObtainedMarks;

    String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public GradesModel(String date, String totalMarks, String obtainedMarks, String type) {
        this.date = date;
        TotalMarks = totalMarks;
        ObtainedMarks = obtainedMarks;
        Type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        TotalMarks = totalMarks;
    }

    public String getObtainedMarks() {
        return ObtainedMarks;
    }

    public void setObtainedMarks(String obtainedMarks) {
        ObtainedMarks = obtainedMarks;
    }
}
