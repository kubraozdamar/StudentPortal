package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.Survey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query(value = "SELECT * FROM survey", nativeQuery = true)
    public List<Survey> findAll();

    @Query(value = "SELECT * FROM survey WHERE user_id = :userId", nativeQuery = true)
    public List<Survey> findByUserId(@Param("userId") long userId);

    @Query(value = "SELECT id from survey s WHERE s.course_id = :courseId", nativeQuery = true)
    public long findIdByCourseId(@Param("courseId") long courseId);

    @Query(value = "SELECT * from survey s WHERE s.course_id = :courseId", nativeQuery = true)
    public Survey findByCourseId(@Param("courseId") long courseId);

    @Query(value = "SELECT course_id from survey WHERE id = :id", nativeQuery = true)
    public long findCourseIdById(@Param("id") long id);

}
