package com.petception.response;

import com.petception.interfaces.IUserResponse;

/**
 * Created by manjtsingh on 6/5/2016.
 */
public class PetAddResponse implements IUserResponse {

    private String errorMessage;
    private String status;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    private String petId;

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

    @Override
    public void setStatus(String status) {
        this.status=status;
    }
}
