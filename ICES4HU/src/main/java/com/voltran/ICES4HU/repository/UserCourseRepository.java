package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.Course;
import com.voltran.ICES4HU.models.User;
import com.voltran.ICES4HU.models.UserCourse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    @Query(value = "SELECT id FROM user_course uc WHERE uc.course_id = :courseId", nativeQuery = true)
    public List<Long> findIdByCourseId(@Param("courseId") long courseId);

    public List<UserCourse> findByCourseId(long courseId);

    @Query(value = "SELECT user_id FROM user_course uc WHERE uc.course_id = :courseId", nativeQuery = true)
    public List<Long> findUserIdByCourseId(@Param("courseId") long courseId);

    public List<UserCourse> findByUserId(long userId);

    @Query(value = "SELECT course_id FROM user_course WHERE user_id = :userId", nativeQuery = true)
    List<Long> findCourseIdByUserId(@PathVariable("userId") long userId);

    @Query(value = "SELECT final_grade FROM user_course uc WHERE uc.id = :id", nativeQuery = true)
    public int findFinalGradeById(@Param("id") long id);

    @Query(value = "SELECT done_percentage FROM user_course uc WHERE uc.id = :id", nativeQuery = true)
    public int findDonePercentageById(@Param("id") long id);

    @Query(value = "SELECT course_id FROM user_course WHERE user_id = :userId", nativeQuery = true)
    public List<Long> findCourseByUserId(@Param("userId") long userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user_course SET final_grade= :finalGrade, done_percentage = :donePercentage WHERE id= :id", nativeQuery = true)
    public void updateFinalGradeAndDonePercentageById(@Param("finalGrade") long finalGrade, @Param("donePercentage") long donePercentage, @Param("id") long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserCourse AS u WHERE u.id= :id")
    void deleteUserCourseById(@Param("id") long id);

}
