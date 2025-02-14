package com.voltran.ICES4HU.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "survey_info")
public class SurveyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "survey_id")
    private long surveyId;

    private String question;

    private float answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public float getAnswer() {
        return answer;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }
}

