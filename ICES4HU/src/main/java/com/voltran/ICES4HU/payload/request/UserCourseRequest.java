package com.voltran.ICES4HU.payload.request;
import javax.validation.constraints.NotBlank;

public class UserCourseRequest {
    @NotBlank
    private Long userId;

    @NotBlank
    private Long courseId;



    public UserCourseRequest(@NotBlank Long userId, @NotBlank Long courseId){
        this.courseId=courseId;
        this.userId=userId;
    }

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId= userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
