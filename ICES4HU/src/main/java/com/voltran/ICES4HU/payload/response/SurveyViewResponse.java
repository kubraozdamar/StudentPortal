package com.voltran.ICES4HU.payload.response;
import com.voltran.ICES4HU.models.Survey;

import java.util.Date;
import java.util.List;

public class SurveyViewResponse {

    private String courseName;
    private List<String> questions;
    private List<Float> answers;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<Float> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Float> answers) {
        this.answers = answers;
    }

}
