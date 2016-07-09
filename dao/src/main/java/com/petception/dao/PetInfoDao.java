package com.petception.dao;

import com.petception.mongo.PetAccessLayer;
import com.petception.pet.Pet;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@Component
public class PetInfoDao {

    @Autowired
    private PetAccessLayer petAccessLayer;

    public List<Pet> getAllPetInfo()
    {
        List<Document> pets = petAccessLayer.getAllPetInfo();
        return pets.stream().map(this::mapDocumentToPetInfo).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public List<Pet> getPetInfo(String petIdentifier)
    {
       Document response = petAccessLayer.getPetInfo(petIdentifier);
       if(response==null)
       {
           return null;
       }
       return mapDocumentToPetInfo(response);
    }

    private List<Pet> mapDocumentToPetInfo(Document jsonResponse) {
        List<Document> responses = (List)jsonResponse.get("pets");
        List<Pet> pets = new ArrayList<>();
        for(Document response : responses)
        {
            Pet pet = new Pet();
            pet.setAge(response.getInteger("age"));
            pet.setBreed(response.getString("breed"));
            pet.setColor(response.getString("color"));
            pet.setPetId(response.getString("petId"));
            pet.setWeight(response.getInteger("weight"));
            pet.setName(response.getString("name"));
            pet.setUrl(response.getString("url"));
            pet.setVaccine(response.getString("vaccine"));
            pet.setType(response.getString("type"));
            pet.setComment(response.getString("comment"));
            pets.add(pet);
        }
        return pets;
    }

    public String addPetInfo(Pet pet) {
        return petAccessLayer.addPetInfo(pet).getString("petId");
    }

    public String uploadPic(MultipartFile file) throws IOException {
        return petAccessLayer.uploadPic(file);
    }

    public String getPic(String petId) throws IOException {
        return petAccessLayer.getPic(petId);
    }
}
