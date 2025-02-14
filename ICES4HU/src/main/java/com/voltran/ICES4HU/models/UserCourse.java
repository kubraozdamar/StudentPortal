package com.voltran.ICES4HU.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_course")

public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "course_id")
    private long courseId;

    @Column(name = "final_grade")
    private int finalGrade;

    @Column(name = "done_percentage")
    private int donePercentage;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public int getDonePercentage() {
        return donePercentage;
    }

    public void setDonePercentage(int donePercentage) {
        this.donePercentage = donePercentage;
    }

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }
}
