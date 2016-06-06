package com.petception.response;

import com.petception.interfaces.IUserResponse;
import com.petception.user.User;

/**
 * Created by manjtsingh on 6/5/2016.
 */
public class LoginResponse implements IUserResponse {

    private String errorMessage;
    private String status;
    private User user;

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public void setErrorMessage(String message) {
        this.errorMessage=message;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
