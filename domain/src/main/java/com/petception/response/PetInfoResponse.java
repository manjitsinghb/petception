package com.petception.response;

import com.petception.interfaces.IUserResponse;
import com.petception.pet.Pet;

/**
 * Created by manjtsingh on 6/5/2016.
 */
public class PetInfoResponse implements IUserResponse {

    private String errorMessage;
    private String status;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    private Pet pet;

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
