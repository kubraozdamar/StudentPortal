package com.voltran.ICES4HU.controller;

import com.voltran.ICES4HU.models.*;
import com.voltran.ICES4HU.payload.element.AddDropRow;
import com.voltran.ICES4HU.payload.element.AddDropRowVisual;
import com.voltran.ICES4HU.payload.request.CreateCourseRequest;
import com.voltran.ICES4HU.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserCoursePendRepository userCoursePendRepository;

    @Autowired
    private FacultyRepository facultyRepository;


    @PostMapping("/adddrop/accept/{userId}")
    public void acceptAddDrop(@PathVariable("userId") long userId){

        List<UserCoursePend> ucpList = userCoursePendRepository.findByUserId(userId);

        UserCourse uc;

        for(UserCoursePend ucp : ucpList){
            uc = new UserCourse();
            uc.setUserId(ucp.getUserId());
            uc.setCourseId(ucp.getCourseId());
            userCourseRepository.save(uc);
            userCoursePendRepository.deleteById(ucp.getId());
        }

    }


    @PostMapping("/adddrop/reject/{userId}")
    public void rejectAddDrop(@PathVariable("userId") long userId){
        List<UserCoursePend> ucpList = userCoursePendRepository.findByUserId(userId);
        for(UserCoursePend ucp : ucpList){
            userCoursePendRepository.deleteById(ucp.getId());
        }
    }


    @GetMapping("/adddrop/check/{userId}")
    public List<AddDropRowVisual> listAddDropPendingOfUser(@PathVariable("userId") long userId){

        List<UserCoursePend> ucpList = userCoursePendRepository.findByUserId(userId);

        List<AddDropRowVisual> r = new ArrayList<AddDropRowVisual>();

        Course course;

        for (UserCoursePend ucp : ucpList){

            course = courseRepository.findById(ucp.getCourseId()).get();
            r.add(new AddDropRowVisual(course.getNameCode(), course.getName()));
        }

        return r;
    }

    @PostMapping("/adddrop/apply/{userId}")
    public void applyCourse(@Validated @RequestBody List<AddDropRow> addDropRowList,@PathVariable("userId") long userId){

        for(int i = 0; i < addDropRowList.size(); i++){

            UserCoursePend ucp = new UserCoursePend();

            ucp.setUserId(userId);
            ucp.setCourseId(addDropRowList.get(i).courseId);
            ucp.setIsAdd(addDropRowList.get(i).isAdd);

            userCoursePendRepository.save(ucp);

        }

    }


    @PostMapping("/adddrop/add/{userId}/{courseId}")
    public void addDropAdd(@PathVariable("userId") long userId, @PathVariable("courseId") long courseId){

        UserCoursePend ucp = new UserCoursePend();

        ucp.setUserId(userId);
        ucp.setCourseId(courseId);

        userCoursePendRepository.save(ucp);

    }


    @PostMapping("/adddrop/drop/{userId}/{courseId}")
    public void addDropDrop(@PathVariable("userId") long userId, @PathVariable("courseId") long courseId){

        userCoursePendRepository.deleteById(userCoursePendRepository.findIdByUserIdAndCourseId(userId, courseId));

    }



    @GetMapping("/adddrop/pend/list")
    public List<User> getAddDropStudentList(){

        List<Long> userIds = userCoursePendRepository.findUniqueUserId();

        List<User> r = new ArrayList<User>();

        for(long id : userIds){
            r.add(userRepository.findById(id).get());
        }

        return r;

    }

    @GetMapping("/list")
    public List<Course> getAllCourses(){
        System.out.println(courseRepository.findAll().get(0).getName());
        return courseRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public List<Course> getUserCourses(){
        System.out.println(courseRepository.findAll().get(0).getName());
        return courseRepository.findAll();
    }

    @GetMapping("/instructor/list")
    public List<User> getInstructors(){
        return userRepository.findAllInstructors();
    }

    @GetMapping("/faculty/list")
    public List<Faculty> getFacultyList(){
        return facultyRepository.listAll();
    }

    @GetMapping("/getname/{id}")
    public String getCourseName(@PathVariable(value = "id") long id){
        System.out.println(courseRepository.findById(id).get().getName());
        return courseRepository.findById((long) id).get().getName();
    }

    @PostMapping("/create")
    public Course createCourse(@Validated @RequestBody Course course){

        long fid;

        System.out.println(course.getInstructorId());
        User u = userRepository.findById((long)course.getInstructorId()).get();

        String fName = u.getFaculty();

        System.out.println(fName);

        fid = facultyRepository.findIdByName(fName);

        System.out.println(fid);

        course.setDepartmentId((int)fid);

        courseRepository.save(course);
        return course;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCourse(@PathVariable(value = "id") long id){
        Course course = courseRepository.findById((long) id).get();
        courseRepository.delete(course);
        Map<String, Boolean> answer = new HashMap<>();
        answer.put("Deleted Success",Boolean.TRUE);

        return ResponseEntity.ok(answer);
    }

    @PostMapping("/quota/{id}")
    public void updateQuota(@PathVariable(value="id") long id , @Validated @RequestBody CreateCourseRequest quotaRequest){
        courseRepository.updateQuotaById(quotaRequest.getQuota(), id);
    }

    @PostMapping("/increaseQuota/{id}")
    public void increaseQuota(@PathVariable(value="id") long id , @Validated @RequestBody CreateCourseRequest quotaRequest){
        Course course = courseRepository.findCourseById(id);
        courseRepository.updateQuotaById(quotaRequest.getQuota() + course.getQuota(), id);
    }

    @PostMapping("/edit/{id}")
    public void editCourse(@PathVariable(value="id") long id , @Validated @RequestBody CreateCourseRequest createCourseRequest){

        if(createCourseRequest.getAkts() != 0)
            courseRepository.updateAktsById(createCourseRequest.getAkts(),id);
        if(createCourseRequest.getDepartmentId() != 0)
            courseRepository.updateDepartmentById(createCourseRequest.getDepartmentId(),id);
        if(createCourseRequest.getEndTime() != null)
            courseRepository.updateEndTimeById(createCourseRequest.getEndTime(),id);
        if(createCourseRequest.getStartTime() != null)
            courseRepository.updateStartTimeById(createCourseRequest.getStartTime(),id);
        if(createCourseRequest.getInstructor() != null)
            courseRepository.updateInstructorById(createCourseRequest.getInstructor(),id);
        if(createCourseRequest.getLocation() != null)
            courseRepository.updateLocationById(createCourseRequest.getLocation(),id);
        if(createCourseRequest.getName() != null)
            courseRepository.updateNameById(createCourseRequest.getName(),id);
        if(createCourseRequest.getSemesterId() != 0)
            courseRepository.updateSemesterById(createCourseRequest.getSemesterId(),id);
        if(createCourseRequest.getNameCode() != null)
            courseRepository.updateNameCodeById(createCourseRequest.getNameCode(),id);
        if(createCourseRequest.getQuota() != 0)
            courseRepository.updateQuotaById(createCourseRequest.getQuota(),id);

    }

    @GetMapping("/show/{id}")
    public Course showCourse(@PathVariable(value = "id") long id){
        return courseRepository.findCourseById(id);
    }


    /*@GetMapping("/exam/add")
    public void addExam(Exam exam){
        examRepository.save(exam);
    }*/

    /*@GetMapping("/exam/list")
    public void listExams(Exam exam){
        examRepository.save(exam);
    }*/


    //========== SPECIFIC LISTS =================================

    @GetMapping("/list/student/ad/{userId}")
    public List<Course> listCourseStudentAddDrop(@PathVariable("userId") long userId){
        List<Course> cl = listCourseByFaculty(facultyRepository.findIdByName(userRepository.findFacultyById(userId)));

        List<Long> scil = userCoursePendRepository.findCourseIdByUserId(userId);

        List<Long> sl = userCourseRepository.findCourseByUserId(userId);

        scil.addAll(sl);

        List<Course> r = new ArrayList<Course>();

        for (long sci : scil){

            System.out.println(sci);

        }

        boolean isContains = false;
        long ci;

        for(Course c : cl){
            isContains = false;
            ci = c.getId();

            for (Long sci : scil){
                if(sci.longValue() == ci){
                    isContains = true;
                    break;
                }
            }

            if(!isContains){
                r.add(c);
            }
        }

        return r;
    }


    @GetMapping("/list/student/adself/{userId}")
    public List<Course> listCourseStudentAddDropSelf(@PathVariable("userId") long userId){
        List<Long> scil = userCoursePendRepository.findCourseIdByUserId(userId);
        List<Course> r = new ArrayList<Course>();
        for(Long sci : scil){
            r.add(courseRepository.findById(sci).get());
        }
        return r;
    }



    @GetMapping("/instructor/list/faculty/{facultyId}")
    public List<User> listInstructorByFaculty(@PathVariable("facultyId") long facultyId){
        return userRepository.findInstructorByFacultyName(facultyRepository.findNameById(facultyId));
    }


    @GetMapping("/list/faculty/{facultyId}")
    public List<Course> listCourseByFaculty(@PathVariable("facultyId") long facultyId){
        return courseRepository.findByFacultyId(facultyId);
    }


    @GetMapping("/list/instructor/{instructorId}")
    public List<Course> listCourseByInstructor(@PathVariable("instructorId") long instructorId){
        return courseRepository.findByInstructorId(instructorId);
    }


    @GetMapping("/list/student/{studentId}")
    public List<Course> listCourseByStudent(@PathVariable("studentId") long studentId){
        List<Long> courseIdList = userCourseRepository.findCourseIdByUserId(studentId);
        List<Course> r = new ArrayList<Course>();
        for(Long cId : courseIdList){
            r.add(courseRepository.findById(cId).get());
        }
        return r;
    }


}
