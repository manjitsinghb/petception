package com.petception.app.controller;

import com.petception.dao.PetInfoDao;
import com.petception.enums.ServerStatus;
import com.petception.pet.Pet;
import com.petception.request.PetAddRequest;
import com.petception.request.PetInfoRequest;
import com.petception.response.PetAddResponse;
import com.petception.response.PetInfoResponse;
import com.petception.validator.AddPetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@RestController
@CrossOrigin(origins = "*")
public class PetInfoController {

    @Autowired
    PetInfoDao petInfoDao;

    @Autowired
    AddPetValidator addPetValidator;

    @Value("${breed.dog}")
    private String dogBreed;

    private String catBreed;

    @RequestMapping(value = "/getPet",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public @ResponseBody PetInfoResponse getPetInfo(@RequestBody PetInfoRequest petInfoRequest)
    {
        String identifier = petInfoRequest.getPetId();
        List<Pet> pet = petInfoDao.getPetInfo(identifier);
        PetInfoResponse response = createResponse();
        if(pet ==null)
        {
            response.setErrorMessage("Failed to get Pet Information");
            response.setStatus(ServerStatus.FAILED.name());
            return response;
        }
        response.setPet(pet);
        return response;
    }

    @RequestMapping(value = "/getBreed",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public @ResponseBody String getBreed(@RequestBody Map<String, String> breeds)
    {
        String breed = breeds.get("breed");
        if("dog".equalsIgnoreCase(breed)) {
            return dogBreed;
        }
        else if("cat".equalsIgnoreCase(breed))
        {
            return catBreed;
        }
        return null;
    }


    @RequestMapping(value = "/addPet",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public @ResponseBody PetAddResponse addPet(@RequestBody PetAddRequest petAddRequest)
    {
        Pet pet = petAddRequest.getPet();
        List<String> errors = addPetValidator.validate(pet);
        if(!errors.isEmpty())
        {
            PetAddResponse petAddResponse = new PetAddResponse();
            petAddResponse.setErrorMessage(StringUtils.collectionToDelimitedString(errors,","));
            petAddResponse.setStatus(ServerStatus.FAILED.name());
            return petAddResponse;
        }
        pet.setPetId(UUID.randomUUID().toString());
        String result = petInfoDao.addPetInfo(pet);
        PetAddResponse response = createPetAddResponse();
        if(result ==null)
        {
            response.setErrorMessage("Failed to add Pet Information");
            response.setStatus(ServerStatus.FAILED.name());
            return response;
        }
        response.setPetId(result);
        response.setStatus(ServerStatus.SUCCESS.name());
        return response;
    }


    @RequestMapping(value = "/getAllPets",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public @ResponseBody PetInfoResponse getAllPets()
    {
        List<Pet> pet = petInfoDao.getAllPetInfo();
        PetInfoResponse response = createResponse();
        response.setPet(pet);
        return response;
    }

    private PetInfoResponse createResponse() {
        PetInfoResponse response = new PetInfoResponse();
        response.setStatus(ServerStatus.SUCCESS.name());
        return response;
    }

    private PetAddResponse createPetAddResponse()
    {
        PetAddResponse response = new PetAddResponse();
        response.setStatus(ServerStatus.SUCCESS.name());
        return response;
    }

}
