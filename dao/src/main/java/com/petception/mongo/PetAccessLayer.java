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
        Document filter = new Document("pets.petId",petIdentifier);
        List<Document> results = petInfoCollection.find(filter).into(new ArrayList<>());
        if(results.size()==0)
        {
            return null;
        }
        return results.get(0);
    }

    public Document addPetInfo(Pet pet) {
        Document insertDoc = new Document("name",pet.getName()).append("age",pet.getAge()).append("breed",pet.getBreed())
                .append("color",pet.getColor()).append("weight",pet.getWeigthInLbs()).append("petId",pet.getPetId());

        Document findDoc = new Document("_id",pet.getEmail());
        List<Document> findResult = petInfoCollection.find(findDoc).into(new ArrayList<>());
        List<Document> addRow = new ArrayList<>();
        if(findResult.size()==0)
        {
            addRow.add(insertDoc);
            insertDoc.append("_id",pet.getEmail());
            petInfoCollection.insertOne(new Document().append("_id",pet.getEmail()).append("pets", addRow));
            return insertDoc;
        }
        petInfoCollection.updateOne(new Document("_id",pet.getEmail()),new Document().append("$push",new Document("pets",insertDoc)));
        return insertDoc;
    }

    public List<Document> getAllPetInfo() {
        Document properties = new Document();
        return petInfoCollection.find(properties).into(new ArrayList<>());
    }
}
