package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.Course;
import com.voltran.ICES4HU.models.Semester;
import com.voltran.ICES4HU.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {



}