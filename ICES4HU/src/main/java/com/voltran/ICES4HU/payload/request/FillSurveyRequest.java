package com.voltran.ICES4HU.payload.request;

import java.util.List;

public class FillSurveyRequest {

    public long userId;
    public List<Long> surveyInfoIds;
    public List<Integer> answers;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Long> getSurveyInfoIds() {
        return surveyInfoIds;
    }

    public void setSurveyInfoIds(List<Long> surveyInfoIds) {
        this.surveyInfoIds = surveyInfoIds;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
