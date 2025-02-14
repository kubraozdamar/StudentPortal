package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.Faculty;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {


    @Query(value = "SELECT * FROM faculty f", nativeQuery = true)
    public List<Faculty> listAll();

    @Query(value = "SELECT name FROM faculty WHERE id = :id", nativeQuery = true)
    String findNameById(@Param("id") long id);

    @Query(value = "SELECT id FROM faculty WHERE name = :name", nativeQuery = true)
    long findIdByName(@Param("name") String name);

}
