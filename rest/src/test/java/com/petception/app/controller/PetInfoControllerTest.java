package com.petception.app.controller;

import com.petception.dao.PetInfoDao;
import com.petception.pet.Pet;
import com.petception.response.PetInfoResponse;
import com.petception.validator.AddPetValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjtsingh on 6/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PetInfoControllerTest {

    @InjectMocks
    PetInfoController petInfoController = new PetInfoController();

    @Mock
    PetInfoDao petInfoDao;

    @Spy
    AddPetValidator addPetValidator = new AddPetValidator();

    @Before
    public void before()
    {
        Mockito.when(petInfoDao.getAllPetInfo()).thenReturn(getAllPets());
    }

    private List<Pet> getAllPets() {
        Pet pet = getPet();
        List<Pet> petList = new ArrayList<Pet>();
        petList.add(pet);
        return petList;
    }

    private Pet getPet() {
        Pet pet = new Pet();
        pet.setName("name");
        pet.setPetId("id");
        pet.setAge(1);
        pet.setBreed("breed");
        pet.setColor("color");
        pet.setUserId("userId");
        pet.setWeigthInLbs(1);
        pet.setComment("comment");
        return pet;
    }


    @Test
    public void testGetAllPets()
    {
        PetInfoResponse returnInfo = petInfoController.getAllPets();
        Assert.assertEquals(1,returnInfo.getPet().size());
        Assert.assertTrue(StringUtils.isEmpty(returnInfo.getErrorMessage()));
        Assert.assertEquals("SUCCESS",returnInfo.getStatus());
    }

}
