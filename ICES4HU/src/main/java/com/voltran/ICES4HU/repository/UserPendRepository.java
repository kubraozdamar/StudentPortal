package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.UserPend;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserPendRepository extends JpaRepository<UserPend, Long> {

    @Query(value = "SELECT up.id FROM user_pend as up WHERE up.email= :email", nativeQuery = true)
    public Long findIdByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user_pend as up", nativeQuery = true)
    public List<UserPend> findAll();

}
