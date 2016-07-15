package com.petception.auth;

import com.petception.enums.ServerStatus;
import com.petception.user.User;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manjtsingh on 7/14/2016.
 */
@XmlRootElement
public class UserResponse {
   private ServerStatus status;
    private User userInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public void setStatus(ServerStatus status) {
        this.status = status;
    }



}
