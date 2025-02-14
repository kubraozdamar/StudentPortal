package com.voltran.ICES4HU.repository;

import com.voltran.ICES4HU.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT user.name FROM User as user WHERE user.id= :userId")
    String findNameById(@Param("userId") long userId);

    @Query("SELECT user FROM User as user WHERE user.id= :userId")
    User findUserById(@Param("userId") long userId);

    @Query("SELECT user.id FROM User as user WHERE user.email= :email")
    Long findIdByEmail(@Param("email") String email);

    @Query("SELECT user.id FROM User as user WHERE user.student_number= :studentNumber")
    Long findIdByStudentNumber(@Param("studentNumber") long studentNumber);

    @Query("SELECT u FROM User u WHERE u.role_id = 2")
    List<User> findAllStudents();

    @Query(value = "SELECT * FROM user u WHERE u.role_id = 3", nativeQuery = true)
    List<User> findAllInstructors();

    @Query(value = "SELECT * FROM user WHERE role_id > 3 AND faculty = :facName", nativeQuery = true)
    List<User> findInstructorByFacultyName(@Param("facName") String facName);

    @Query(value = "SELECT faculty FROM user WHERE id = :id", nativeQuery = true)
    String findFacultyById(@Param("id") long id);

    @Query(value = "SELECT u.role_id FROM user u WHERE u.id = :id", nativeQuery = true)
    int findRoleById(@Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user SET name= :name WHERE id= :id", nativeQuery = true)
    int updateNameById(@Param("name") String name,@Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user SET student_number= :studentNumber WHERE id= :id", nativeQuery = true)
    int updateStudentNumberById(@Param("studentNumber") long studentNumber, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user SET password= :password WHERE id= :id", nativeQuery = true)
    int updatePasswordById(@Param("password") String password, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user SET faculty= :faculty WHERE id = :id", nativeQuery = true)
    int updateFacultyById(@Param("faculty") String faculty, @Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user SET is_graduate= 1 WHERE id = :id", nativeQuery = true)
    int updateIsGraduateById(@Param("id") long id);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE user SET email= :email WHERE id = :id", nativeQuery = true)
    int updateEmailById(@Param("email") String email, @Param("id") long id);



}
