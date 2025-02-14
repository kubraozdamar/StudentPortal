package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {

    @Query(value = "SELECT AVG(answer) FROM survey_answer WHERE survey_info_id = :siId", nativeQuery = true)
    float findAvgOfAnswerBySurveyInfoId(@PathVariable("siId") long siId);

}
