package com.voltran.ICES4HU.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exam")

public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "course_id")
    private long courseId;

    @Column(name = "percentage")
    private int percentage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }


}
