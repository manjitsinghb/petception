package com.petception.mongo;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.petception.pet.Pet;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@Repository
public class PetAccessLayer extends BaseRepository{

    @Autowired
    MongoClient mongoClient;

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

    public String uploadPic(MultipartFile file) throws IOException {
        byte[] fileBytes = file.getBytes();
        String fileName = UUID.randomUUID().toString();
        GridFS gridFS = new GridFS(mongoClient.getDB("petception"),"photoStore");
        GridFSInputFile storedFile = gridFS.createFile(fileBytes);
        storedFile.setFilename(fileName);
        storedFile.setContentType("photo");
        storedFile.save();
        return fileName;
    }
}
