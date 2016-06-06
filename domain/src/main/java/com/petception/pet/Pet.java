package com.petception.pet;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@XmlRootElement
public class Pet {

    private String petId;
    private String name;
    private String breed;
    private Integer weigthInLbs;
    private Integer age;
    private String color;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getWeigthInLbs() {
        return weigthInLbs;
    }

    public void setWeigthInLbs(Integer weigthInLbs) {
        this.weigthInLbs = weigthInLbs;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
