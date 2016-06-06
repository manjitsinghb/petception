package com.petception.mongo;

import com.petception.pet.Pet;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@Repository
public class PetAccessLayer extends BaseRepository{

    public Document getPetInfo(String petIdentifier) {
        Document filter = new Document("petId",petIdentifier);
        List<Document> results = petInfoCollection.find(filter).into(new ArrayList<Document>());
        if(results.size()==0)
        {
            return null;
        }
        return results.get(0);
    }

    public Document addPetInfo(Pet pet) {
        Document properties = new Document("name",pet.getName()).append("age",pet.getAge()).append("breed",pet.getBreed())
                .append("color",pet.getColor()).append("weight",pet.getWeigthInLbs()).append("petId",pet.getPetId());
        petInfoCollection.insertOne(properties);
        return properties;
    }
}
