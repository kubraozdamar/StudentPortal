package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.UserCoursePend;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserCoursePendRepository extends JpaRepository<UserCoursePend, Long> {

    @Query(value = "SELECT * FROM user_course_pend WHERE user_id= :userId", nativeQuery = true)
    List<UserCoursePend> findByUserId(@Param("userId") long userId);

    @Query(value = "SELECT DISTINCT user_id FROM user_course_pend", nativeQuery = true)
    List<Long> findUniqueUserId();

    @Query(value = "SELECT id FROM user_course_pend WHERE user_id = :userId AND course_id = :courseId", nativeQuery = true)
    long findIdByUserIdAndCourseId(@Param("userId") long userId, @Param("courseId") long courseId);

    @Query(value = "SELECT course_id FROM user_course_pend WHERE user_id = :userId", nativeQuery = true)
    List<Long> findCourseIdByUserId(@Param("userId") long userId);

}
