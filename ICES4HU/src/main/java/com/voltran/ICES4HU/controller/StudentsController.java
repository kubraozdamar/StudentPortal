package com.voltran.ICES4HU.controller;


import com.voltran.ICES4HU.models.Exam;
import com.voltran.ICES4HU.models.User;
import com.voltran.ICES4HU.models.UserCourse;
import com.voltran.ICES4HU.payload.element.StudentGradePair;
import com.voltran.ICES4HU.repository.ExamRepository;
import com.voltran.ICES4HU.repository.UserRepository;
import com.voltran.ICES4HU.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/list")
    public List<User> listStudents(){
        return userRepository.findAllStudents();
    }

    @PostMapping("/approvegrad/{userId}")
    public void approveGrad(@PathVariable(value = "userId") long userId){
        userRepository.updateIsGraduateById(userId);
    }

    @GetMapping("/examtakers/{examId}")
    public List<User> showExamTakers(@PathVariable(value = "examId") long examId){

        Optional<Exam> exam = examRepository.findById(examId);
        List<Long> userIds =  userCourseRepository.findUserIdByCourseId(exam.get().getCourseId());

        for (long id : userIds){
            System.out.println(id);
        }

        List<User> examTakers = new ArrayList<User>();

        for(long userId : userIds){
            examTakers.add(userRepository.findById(userId).get());
        }

        return examTakers;

    }

    @PostMapping("/entergrades/{examId}")
    public void enterGrades(@RequestBody @Validated List<StudentGradePair> studentGradeList, @PathVariable(value = "examId") long examId){

        Exam exam = examRepository.findById(examId).get();
        List<Long> ucIds = userCourseRepository.findIdByCourseId(exam.getCourseId());

        for(long id : ucIds){

            UserCourse uc = userCourseRepository.findById(id).get();
            int grade = findGradeByUserId(uc.getUserId(), studentGradeList);

            int finalGrade = userCourseRepository.findFinalGradeById(id);
            int donePercentage = userCourseRepository.findDonePercentageById(id);

            finalGrade += (float) exam.getPercentage() / 100.0f * (float)grade;
            donePercentage += exam.getPercentage();

            userCourseRepository.updateFinalGradeAndDonePercentageById(finalGrade, donePercentage, id);
        }

    }

    private int findGradeByUserId(long userID, List<StudentGradePair> studentGradeList){
        for(int i = 0; i < studentGradeList.size(); i++){
            if(studentGradeList.get(i).userId == userID) {
                return studentGradeList.get(i).grade;
            }
        }
        return 0;
    }

}
