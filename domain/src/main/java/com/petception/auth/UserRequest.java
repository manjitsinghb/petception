package com.petception.auth;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manjtsingh on 7/14/2016.
 */
@XmlRootElement
public class UserRequest {
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

}
