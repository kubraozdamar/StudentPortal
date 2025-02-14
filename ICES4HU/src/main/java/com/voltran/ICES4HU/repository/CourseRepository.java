package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.Course;
import com.voltran.ICES4HU.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String name);
    @Query("SELECT course FROM Course as course WHERE course.id= :courseId")
    Course findCourseById(@Param("courseId") long courseId);

    @Query(value = "SELECT name FROM course WHERE id = :id", nativeQuery = true)
    String findNameById(@Param("id") long id);


    @Query(value = "SELECT * FROM course WHERE department_id = :facultyId", nativeQuery = true)
    List<Course> findByFacultyId(@Param("facultyId") long facultyId);


    @Query(value = "SELECT * FROM course WHERE instructor_id = :instructorId", nativeQuery = true)
    List<Course> findByInstructorId(@Param("instructorId") long instructorId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET quota= :quota WHERE id= :id", nativeQuery = true)
    int updateQuotaById(@Param("quota") int quota, @Param("id") long id);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET name= :name WHERE id= :id", nativeQuery = true)
    int updateNameById(@Param("name") String name, @Param("id") long id);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET akts= :akts WHERE id= :id", nativeQuery = true)
    int updateAktsById(@Param("akts") int akts, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET name_code= :nameCode WHERE id= :id", nativeQuery = true)
    int updateNameCodeById(@Param("nameCode") String nameCode, @Param("id") long id);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET instructor= :instructor WHERE id= :id", nativeQuery = true)
    int updateInstructorById(@Param("instructor") String instructor, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET location= :location WHERE id= :id", nativeQuery = true)
    int updateLocationById(@Param("location") String location, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET start_time= :startTime WHERE id= :id", nativeQuery = true)
    int updateStartTimeById(@Param("startTime") Date startTime, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET end_time= :endTime WHERE id= :id", nativeQuery = true)
    int updateEndTimeById(@Param("endTime") Date endTime, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET department_id= :departmentId WHERE id= :id", nativeQuery = true)
    int updateDepartmentById(@Param("departmentId") int departmentId, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET semester_id= :semesterId WHERE id= :id", nativeQuery = true)
    int updateSemesterById(@Param("semesterId") int semesterId, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE course SET has_survey = 1 WHERE id= :id", nativeQuery = true)
    int updateHasSurveyById(@Param("id") long id);


}