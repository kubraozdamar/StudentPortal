package com.voltran.ICES4HU.payload.element;

public class StudentGradePair {

    public long userId;
    public int grade;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
