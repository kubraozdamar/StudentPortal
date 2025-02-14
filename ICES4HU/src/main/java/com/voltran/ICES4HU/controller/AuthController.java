package com.voltran.ICES4HU.controller;


import com.voltran.ICES4HU.models.StudentInstructor;
import com.voltran.ICES4HU.models.User;
import com.voltran.ICES4HU.models.UserPend;
import com.voltran.ICES4HU.payload.request.RegisterInstructorRequest;
import com.voltran.ICES4HU.payload.request.RegisterRequest;
import com.voltran.ICES4HU.payload.request.SignInRequest;
import com.voltran.ICES4HU.payload.response.AuthResponse;
import com.voltran.ICES4HU.repository.UserRepository;
import com.voltran.ICES4HU.repository.UserPendRepository;
import com.voltran.ICES4HU.repository.StudentInstructorRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPendRepository userPendRepository;

    @Autowired
    private StudentInstructorRepository studentInstructorRepository;

    @PostMapping("/register")
    public AuthResponse register(@Validated @RequestBody RegisterRequest registerRequest){


        Long id = userRepository.findIdByEmail(registerRequest.getEmail());

        if(!Objects.isNull(id)){
            return new AuthResponse(false, "Student with this e-mail address already exists.", null, 0);
        }

        id = userPendRepository.findIdByEmail(registerRequest.getEmail());

        if(!Objects.isNull(id)){
            return new AuthResponse(false, "Student with this e-mail address already exists.", null, 0);
        }

        UserPend userPend = new UserPend();

        userPend.setName(registerRequest.getName());
        userPend.setEmail(registerRequest.getEmail());
        userPend.setStudent_number(registerRequest.getStudentNumber());
        userPend.setPassword(registerRequest.getPassword());
        userPend.setFaculty(registerRequest.getFaculty());

        userPendRepository.save(userPend);

        return new AuthResponse(true, "Enrollment request has done successfully.", userRepository.findIdByEmail(userPend.getEmail()), 0);

    }


    @PostMapping("/register/instructor")
    public AuthResponse registerIns(@Validated @RequestBody RegisterInstructorRequest registerInstructorRequest){

        User user = new User();

        user.setRole_id((long)3);

        user.setName(registerInstructorRequest.getName());
        user.setEmail(registerInstructorRequest.getEmail());
        user.setPassword(registerInstructorRequest.getPassword());
        user.setFaculty(registerInstructorRequest.getFaculty());

        user.setGraduate(false);
        user.setStudent_number((long)0);
        user.setProfileIcon("-");

        userRepository.save(user);

        return new AuthResponse(true, "Enrollment request has done successfully.", userRepository.findIdByEmail(user.getEmail()), 0);

    }


    @GetMapping("/pend/list")
    public List<UserPend> pendingList(){

        return userPendRepository.findAll();

    }

    @PostMapping("/role/{id}")
    public int getRoleId(@PathVariable(value = "id") long id){
        return userRepository.findRoleById(id);
    }

    @PostMapping("/pend/reject/{id}")
    public void rejectPending(@PathVariable(value = "id") long id){
        userPendRepository.deleteById(id);
    }

    @GetMapping("/pend/{id}")
    public UserPend viewPend(@PathVariable("id") long id){
        return userPendRepository.findById(id).get();
    }

    @PostMapping("/pend/accept/{id}")
    public void acceptPending(@PathVariable(value = "id") long id){

        UserPend userPend = userPendRepository.findById(id).get();
        User user = new User();
        user.setName(userPend.getName());
        user.setEmail(userPend.getEmail());
        user.setPassword(userPend.getPassword());
        user.setFaculty(userPend.getFaculty());
        user.setStudent_number(userPend.getStudent_number());

        user.setRole_id((long)2);

        user.setProfileIcon("-");

        userRepository.save(user);
        userPendRepository.deleteById(id);

        long userId = userRepository.findIdByEmail(user.getEmail());

        StudentInstructor si = new StudentInstructor();
        si.setStudentId(userId);
        si.setInstructorId(findInstructorId());

        studentInstructorRepository.save(si);

    }

    long findInstructorId(){
        List<User> insts = userRepository.findAllInstructors();

        int minVal = Integer.MAX_VALUE;
        int minInd = -1;
        int count;

        for(int i = 0; i < insts.size(); i++){
            count = studentInstructorRepository.countInstructor(insts.get(i).getId());
            if(count < minVal){
                minInd = i;
                minVal = count;
            }
        }
        return minInd;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@Validated @RequestBody SignInRequest signInRequest){

        if(signInRequest.getEmail().length()==0)
        {
            return new AuthResponse(false, "E-mail cannot be empty.", null, 0);
        }
        else if(signInRequest.getPassword().length()==0)
        {
            return new AuthResponse(false, "Password cannot be empty.", null, 0);
        }

        Long id = userRepository.findIdByEmail(signInRequest.getEmail());
        if(Objects.isNull(id)){
            return new AuthResponse(false, "User with this e-mail cannot be found.", null, 0);
        }
        Optional<User> optUser = userRepository.findById(id);

        User user=optUser.get();
        if(!(user.getPassword().equals(signInRequest.getPassword())))
        {
            return new AuthResponse(false, "Password is not correct.", user.getId(), 0);
        }
        return new AuthResponse(true, "Sign in operation is successful.", user.getId(), (int)user.getRole_id().longValue());

    }


}
