package com.voltran.ICES4HU.controller;


import com.voltran.ICES4HU.models.Semester;
import com.voltran.ICES4HU.models.User;
import com.voltran.ICES4HU.models.Course;
import com.voltran.ICES4HU.models.UserCourse;
import com.voltran.ICES4HU.payload.element.TranscriptRow;
import com.voltran.ICES4HU.payload.request.EditAccountRequest;
import com.voltran.ICES4HU.payload.request.RegisterRequest;
import com.voltran.ICES4HU.payload.request.SignInRequest;
import com.voltran.ICES4HU.payload.response.AuthResponse;
import com.voltran.ICES4HU.repository.UserRepository;
import com.voltran.ICES4HU.repository.CourseRepository;
import com.voltran.ICES4HU.repository.UserCourseRepository;
import com.voltran.ICES4HU.repository.SemesterRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private SemesterRepository semesterRepository;


    @GetMapping("/transcript/{id}")
    public List<TranscriptRow> transcript(@PathVariable(value = "id") long id){

        // NEED TO TAKE STUDENT NUMBER AS PARAMETER

        List<UserCourse> ucl = userCourseRepository.findByUserId(id);
        Optional<Course> optCourse;
        Course course;
        UserCourse uc;
        Optional<Semester> optSemester;
        Semester semester;
        TranscriptRow tRow;List<TranscriptRow> transcript = new ArrayList<>();

        for (int i = 0; i < ucl.size(); i++){
            uc = ucl.get(i);
            optCourse = courseRepository.findById(uc.getCourseId());
            course = optCourse.get();
            optSemester = semesterRepository.findById(course.getSemesterId());
            semester = optSemester.get();
            tRow = new TranscriptRow(semester.getStartDate(), course.getNameCode(), course.getName(), uc.getDonePercentage(), uc.getFinalGrade(), course.getAkts());
            transcript.add(tRow);
        }

        transcript = transcript.stream().sorted().toList();
        return transcript;

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public User saveUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public String findUserName() {
        return userRepository.findNameById(1);
    }

    @PostMapping("/edit/{id}")
    public User editAccount(@Validated @RequestBody EditAccountRequest editAccountRequest, @PathVariable(value = "id") long id)
    {
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            return null;
        }
        if(editAccountRequest.getName().length() != 0)
            userRepository.updateNameById(editAccountRequest.getName(), id);
        if(editAccountRequest.getPassword().length() != 0)
            userRepository.updatePasswordById(editAccountRequest.getPassword(), id);
        if(editAccountRequest.getFaculty().length() != 0)
            userRepository.updateFacultyById(editAccountRequest.getFaculty(), id);
        if(editAccountRequest.getFaculty().length() != 0)
            userRepository.updateEmailById(editAccountRequest.getEmail(), id);

        return user;
    }


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable(value = "id") long id){
        return userRepository.findById(id).get();
    }


}
