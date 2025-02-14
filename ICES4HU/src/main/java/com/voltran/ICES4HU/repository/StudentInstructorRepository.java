package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.StudentInstructor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StudentInstructorRepository extends JpaRepository<StudentInstructor, Long> {


    @Query(value = "SELECT COUNT(instructor_id) FROM student_instructor as si WHERE si.instructor_id = :instructorId", nativeQuery = true)
    public int countInstructor(@Param("instructorId") long instructorId);

}
