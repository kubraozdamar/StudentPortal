package com.voltran.ICES4HU.controller;

import com.voltran.ICES4HU.models.Survey;
import com.voltran.ICES4HU.models.SurveyAnswer;
import com.voltran.ICES4HU.models.SurveyInfo;
import com.voltran.ICES4HU.payload.request.FillSurveyRequest;
import com.voltran.ICES4HU.payload.response.SurveyViewResponse;
import com.voltran.ICES4HU.repository.*;
import com.voltran.ICES4HU.payload.request.CreateSurveyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyInfoRepository surveyInfoRepository;

    @Autowired
    private SurveyAnswerRepository surveyAnswerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;
    
    @PostMapping("/create")
    public void createSurvey(@RequestBody CreateSurveyRequest request) {

        Survey survey = new Survey();
        survey.setCourseId(request.getCourseId());
        survey.setUserId(request.getUserId());
        survey.setActive(false);
        survey.setStartTime(request.getStartTime());
        survey.setEndTime(request.getEndTime());
        survey.setDraftCount(request.getIsDraft());

        surveyRepository.save(survey);

        courseRepository.updateHasSurveyById(survey.getCourseId());

        long surveyId = surveyRepository.findIdByCourseId(request.getCourseId());

        List<String> questions = request.getQuestions();

        SurveyInfo surveyInfo;

        for(String question : questions){

            surveyInfo = new SurveyInfo();
            surveyInfo.setSurveyId(surveyId);
            surveyInfo.setQuestion(question);
            surveyInfo.setAnswer(0);

            surveyInfoRepository.save(surveyInfo);

        }

    }

    @GetMapping("/show/{surveyId}")
    public SurveyViewResponse viewSurvey(@PathVariable Long surveyId) {
        SurveyViewResponse r = new SurveyViewResponse();

        long courseId = surveyRepository.findCourseIdById(surveyId);
        r.setCourseName(courseRepository.findNameById(courseId));

        List<SurveyInfo> siList = surveyInfoRepository.findBySurveyId(surveyId);

        List<String> questions = new ArrayList<String>();
        //List<Float> answers = new ArrayList<Float>();

        int sum = 0;

        float avg;

        for(SurveyInfo si : siList){
            questions.add(si.getQuestion());
            //answers.add(surveyAnswerRepository.findAvgOfAnswerBySurveyInfoId(si.getId()));
        }

        r.setQuestions(questions);
        //r.setAnswers(answers);
        return r;
    }

    @PostMapping("/start/{surveyId}")
    public ResponseEntity<String> startSurvey(@PathVariable Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        if (survey != null) {
            if (survey.isActive()) {
                survey.setStartTime(new Date());
                surveyRepository.save(survey);
                return ResponseEntity.ok("Survey started successfully.");
            } else {
                return ResponseEntity.badRequest().body("Survey is not active.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/fill/{surveyId}")
    public void fillSurveyQuestionnaire(@Validated @RequestBody FillSurveyRequest fillSurveyRequest, @PathVariable Long surveyId) {
        long userId = fillSurveyRequest.getUserId();
        List<Long> surveyInfoIds = fillSurveyRequest.getSurveyInfoIds();
        List<Integer> answers = fillSurveyRequest.getAnswers();

        SurveyAnswer sa;
        int siId;

        for(int i = 0; i < surveyInfoIds.size(); i++){

            sa = new SurveyAnswer();
            sa.setUserId(userId);
            sa.setSurveyInfoId(surveyInfoIds.get(i));
            sa.setAnswer(answers.get(i));
            surveyAnswerRepository.save(sa);
        }

    }


    @GetMapping("/list")
    public List<Survey> listSurvey(){
        return surveyRepository.findAll();
    }


    //========== SPECIFIC LISTS =================================

    @GetMapping("/list/instructor/{instructorId}")
    public List<Survey> listSurveyByInstructor(@PathVariable("instructorId") long instructorId){
        return surveyRepository.findByUserId(instructorId);
    }


    @GetMapping("/list/student/{studentId}")
    public List<Survey> listSurveyByStudent(@PathVariable("studentId") long studentId){
        List<Long> courseIds = userCourseRepository.findCourseIdByUserId(studentId);

        List<Survey> r = new ArrayList<Survey>();

        for(long cId : courseIds){

            r.add(surveyRepository.findByCourseId(cId));

        }

        return r;

    }



}

