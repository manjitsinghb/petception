package com.petception.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@XmlRootElement
public class PetInfoRequest {
    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    private String petId;

}
