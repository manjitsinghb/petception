package com.petception.response;

import com.petception.interfaces.IUserResponse;
import com.petception.pet.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjtsingh on 6/5/2016.
 */
public class PetInfoResponse implements IUserResponse {

    private String errorMessage;
    private String status;

    public List<Pet> getPet() {
        return pet;
    }

    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

    private List<Pet> pet = new ArrayList<Pet>();

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
