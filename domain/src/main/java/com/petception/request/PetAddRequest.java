package com.petception.request;

import com.petception.pet.Pet;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@XmlRootElement
public class PetAddRequest {
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    private Pet pet;
}
