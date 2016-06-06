package com.petception.interfaces;

/**
 * Created by manjtsingh on 6/5/2016.
 */
public interface IUserResponse {
    String getErrorMessage();
    void setErrorMessage(String message);
    String getStatus();
    void setStatus(String status);
}
