package com.voltran.ICES4HU.payload.element;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Date;

public class TranscriptRow implements Comparable<TranscriptRow> {

    Date semesterDate;

    String semester;

    String courseCode;

    String courseName;

    int akts;

    String grade;

    public TranscriptRow(Date semesterDate, String courseCode, String courseName, int donePercentage, int finalGrade, int akts){

        this.semesterDate = semesterDate;
        this.semester = "";
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.akts = akts;

        int year = 1900 + semesterDate.getYear();

        if(semesterDate.getMonth() > 6){
            this.semester += year + "-" + (year + 1) + " Fall";
        }else{
            this.semester += (year-1) + "-" + year + " Spring";
        }


        grade = "";

        if(donePercentage < 100){
            grade += '-';
        }else if(finalGrade < 50){
            grade += 'F';
        }else {
            grade += finalGrade;
        }

    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAkts() {
        return akts;
    }

    public void setAkts(int akts) {
        this.akts = akts;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public int compareTo(@NotNull TranscriptRow o) {
        int ret = this.semesterDate.compareTo(o.semesterDate);
        if(ret == 0){
            ret = this.courseCode.compareTo(o.courseCode);
        }
        return ret;
    }
}
