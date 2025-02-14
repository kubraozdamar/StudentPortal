package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.SurveyInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyInfoRepository extends JpaRepository<SurveyInfo, Long> {
    @Query(value = "SELECT si.question FROM survey_info si WHERE si.survey_id = :surveyId", nativeQuery = true)
    List<String> findQuestionsBySurveyId(@PathVariable("surveyId") Long surveyId);

    @Query(value = "SELECT * FROM survey_info WHERE survey_id = :surveyId", nativeQuery = true)
    List<SurveyInfo> findBySurveyId(@PathVariable("surveyId") Long surveyId);

}
