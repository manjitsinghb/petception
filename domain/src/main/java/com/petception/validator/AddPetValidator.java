package com.petception.validator;

import com.petception.pet.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjtsingh on 6/10/2016.
 */
@Component
public class AddPetValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddPetValidator.class);

    public List<String> validate(Pet pet)
    {
        List<String> error = new ArrayList<>();
        validateColor(pet,error);
        validateAge(pet,error) ;
        validateWeight(pet,error);
        validateBreed(pet,error);
        validateName(pet,error);
        validateType(pet,error);
        return error;
    }

    private void validateType(Pet pet, List<String> error) {
        LOGGER.info("Validating Type of pet");
        if(StringUtils.isEmpty(pet.getType()))
        {
            error.add("Please select a pet type");
        }
    }

    private void validateName(Pet pet, List<String> error) {
        LOGGER.info("Validating pet name {} for user {}",pet.getBreed(),pet.getUserId());
        if(StringUtils.isEmpty(pet.getName()))
        {
            error.add("Please enter a name");
        }
    }

    private void validateBreed(Pet pet,List<String> error) {
        LOGGER.info("Validating pet breed {} for user {}",pet.getBreed(),pet.getUserId());
        if(StringUtils.isEmpty(pet.getBreed()))
        {
            error.add("Please enter a breed");
        }
    }

    private void validateWeight(Pet pet,List<String> error) {
        LOGGER.info("Validating pet weight {} for user {}",pet.getWeight(),pet.getUserId());
        if(pet.getWeight()==null || pet.getWeight()<=0)
        {
            error.add("Please enter a valid weight");
        }
    }

    private void validateAge(Pet pet,List<String> error) {
        LOGGER.info("Validating pet age {} for user {}",pet.getAge(),pet.getUserId());
        if(pet.getAge()==null || pet.getAge()<0)
        {
            error.add("Please enter a valid age");
        }
    }

    private void validateColor(Pet pet,List<String> error) {
        LOGGER.info("Validating pet color {} for user {}",pet.getColor(),pet.getUserId());
        if(StringUtils.isEmpty(pet.getColor()))
        {
            error.add("Please enter a valid color");
        }
    }
}
