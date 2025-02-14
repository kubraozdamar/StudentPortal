package com.voltran.ICES4HU.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "survey_answer")
public class SurveyAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_info_id")
    private Long surveyInfoId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "answer")
    private float answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getAnswer() {
        return answer;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }
}

