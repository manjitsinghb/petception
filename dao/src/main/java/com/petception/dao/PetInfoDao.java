package com.petception.dao;

import com.petception.filestorage.PetFileLayer;
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

    @Autowired
    private PetFileLayer petFileLayer;

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
            mapPets(pets, response);
        }
        return pets;
    }

    private void mapPets(List<Pet> pets, Document response) {
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
        pet.setVideoUrl(response.getString("videoUrl"));
        pets.add(pet);
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

    public String uploadVideo(MultipartFile file) throws IOException {
        return petFileLayer.uploadVideo(file);

    }

    public String getVideo(String petVideoId) throws IOException {
        return petAccessLayer.getVideo(petVideoId);
    }

    public List<Pet> getAllPetInfo(String username) {
        List<Pet> pets = new ArrayList<>();
        List<Document> petDocs = petAccessLayer.getPetsForUser(username);
        for(Document doc : petDocs)
        {
            pets.addAll(mapDocumentToPetInfo(doc));
        }
        return pets;
    }
}
