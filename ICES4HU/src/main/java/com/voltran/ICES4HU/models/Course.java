package com.voltran.ICES4HU.models;

import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;


@Data
@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;


    private int quota;
    private int akts;

    @Column(name="name_code")
    private String nameCode;
    private int instructorId;
    private String location;
    @Column(name="start_time")
    private Date startTime;
    @Column(name="end_time")
    private Date endTime;
    @Column(name="department_id")
    private int departmentId;
    @Column(name="semester_id")
    long semesterId;

    @Column(name = "has_survey")
    int hasSurvey;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getAkts() {
        return akts;
    }

    public void setAkts(int akts) {
        this.akts = akts;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }

    public int getHasSurvey() {
        return hasSurvey;
    }

    public void setHasSurvey(int hasSurvey) {
        this.hasSurvey = hasSurvey;
    }
}
