package com.petception.mongo;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.petception.pet.Pet;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
        Document insertDoc = new Document("name",pet.getName()).append("age",pet.getAge()).append("breed",pet.getBreed()).append("comment",pet.getComment())
               .append("type",pet.getType()).append("videoUrl",pet.getVideoUrl()).append("color",pet.getColor()).append("weight",pet.getWeight()).append("petId",pet.getPetId()).append("url",pet.getUrl());

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
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String fileName = UUID.randomUUID().toString();
        BufferedImage bImage = reduceImageSize(file);
        ImageIO.write(bImage,file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1),out);
        byte[] fileBytes = Base64.encodeBase64(out.toByteArray());
        GridFS gridFS = new GridFS(mongoClient.getDB("petception"),"photoStore");
        GridFSInputFile storedFile = gridFS.createFile(fileBytes);
        storedFile.setFilename(fileName);
        storedFile.setContentType("photo");
        storedFile.save();
        return fileName;
    }

    private BufferedImage reduceImageSize(MultipartFile multipartFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        int w =  bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage dimg = new BufferedImage(320, 240, bufferedImage.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0,320, 240, 0, 0, w, h, null);
        g.dispose();
        return dimg;


    }

    public String getPic(String petId) throws IOException {
            GridFS gfsPhoto = new GridFS(mongoClient.getDB("petception"), "photoStore");
            GridFSDBFile imageForOutput = gfsPhoto.findOne(petId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if(imageForOutput != null) {
                imageForOutput.writeTo(byteArrayOutputStream);
            }
            return byteArrayOutputStream.toString();
        }finally {
                byteArrayOutputStream.close();
        }
    }

    public String getVideo(String videoId) throws IOException {
        GridFS gfsVideo = new GridFS(mongoClient.getDB("petception"), "videoStore");
        GridFSDBFile imageForOutput = gfsVideo.findOne(videoId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if(imageForOutput != null) {
                imageForOutput.writeTo(byteArrayOutputStream);
            }
            return Base64.decodeBase64(byteArrayOutputStream.toByteArray()).toString();
        }finally {
            byteArrayOutputStream.close();
        }
    }

    public String uploadVideo(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        byte[] fileBytes = Base64.encodeBase64(file.getBytes());
        GridFS gridFS = new GridFS(mongoClient.getDB("petception"),"videoStore");
        GridFSInputFile storedFile = gridFS.createFile(fileBytes);
        storedFile.setFilename(fileName);
        storedFile.setContentType("video");
        storedFile.save();
        return fileName;
    }

    public List<Document> getPetsForUser(String username) {
        Document filter = new Document("_id",username);
        List<Document> results = petInfoCollection.find(filter).into(new ArrayList<>());
        if(results.size()==0)
        {
            return null;
        }
        return results;
    }
}
