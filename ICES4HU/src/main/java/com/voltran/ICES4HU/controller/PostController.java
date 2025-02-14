package com.voltran.ICES4HU.controller;


import com.voltran.ICES4HU.models.*;
import com.voltran.ICES4HU.payload.request.CreatePostRequest;
import com.voltran.ICES4HU.repository.PostRepository;
import com.voltran.ICES4HU.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public void createPost(@Validated @RequestBody CreatePostRequest createPostRequest){

        Post post = new Post();
        post.setUserName(userRepository.findNameById(createPostRequest.getUserId()));
        post.setDate(new Date());
        post.setHeader(createPostRequest.getHeader());
        post.setInfo(createPostRequest.getInfo());
        post.setContent(createPostRequest.getContent());

        postRepository.save(post);

    }

    @GetMapping("/list/{dateAfter}")
    public List<Post> listPosts(@PathVariable(value = "dateAfter") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateAfter){

        dateAfter.setDate(1);
        Date dateBefore = new Date();
        dateBefore.setDate(dateAfter.getDate()); dateBefore.setMonth(dateAfter.getMonth()); dateBefore.setYear(dateAfter.getYear());
        dateBefore.setHours(0); dateBefore.setMinutes(0); dateBefore.setSeconds(0);

        int month = dateBefore.getMonth()+1;
        if(month > 12){
            dateBefore.setYear(dateBefore.getYear()+1);
            dateBefore.setMonth(1);
        }
        else {
            dateBefore.setMonth(month);
        }

        List<Post> postsBetweenDates = postRepository.findAllByPostDateBetween(dateBefore, dateAfter);
        return postsBetweenDates.stream().sorted().toList();

    }


    @GetMapping("/list")
    public List<Post> list(){
        return postRepository.findAll();
    }


    @GetMapping("/show/{postId}")
    public Post showPost(@PathVariable(value = "postId") long postId){
        return postRepository.findById(postId).get();
    }



}

