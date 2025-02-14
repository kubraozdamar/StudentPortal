package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.date < :dateBefore AND p.date >= :dateAfter")
    List<Post> findAllByPostDateBetween(@Param("dateBefore") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dateBefore, @Param("dateAfter") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date dateAfter);

    @Query(value = "SELECT * FROM post", nativeQuery = true)
    List<Post> findAll();

}