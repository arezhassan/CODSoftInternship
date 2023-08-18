package com.example.sunshineschoolsystem;

public class AssignmentsModel {
    private String subject;
    private String description;
    private String dueDate;

    public AssignmentsModel(String subject, String description, String dueDate) {
        this.subject = subject;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }
}
