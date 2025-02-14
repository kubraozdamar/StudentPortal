package com.voltran.ICES4HU.payload.response;

import com.voltran.ICES4HU.models.User;

public class AuthResponse {

    int isSuccessful;
    String message;
    Long userId;
    int roleId;

    public AuthResponse(boolean isSuccessful, String message, Long userId, int roleId){
        if(isSuccessful==true){
            this.isSuccessful = 1;
        }
        else {
            this.isSuccessful = 0;
        }
        this.message = message;
        this.userId = userId;
        this.roleId = roleId;
    }

    public int IsSuccessful(){
        return isSuccessful;
    }

    public String getMessage(){
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public int getRoleId() {
        return roleId;
    }
}
