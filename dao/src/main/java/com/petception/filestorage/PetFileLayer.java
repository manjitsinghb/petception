package com.petception.filestorage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by manjtsingh on 7/13/2016.
 */
@Repository
public class PetFileLayer {

    @Value("${petception.videoPath}")
    private String videoPath;

    public String uploadVideo(MultipartFile file ) throws IOException {
       String fileName = file.getOriginalFilename();
        String randomFileName = UUID.randomUUID().toString();
        String tempFile= randomFileName+fileName.substring(fileName.lastIndexOf("."));
        FileOutputStream writer = new FileOutputStream(videoPath+"/"+tempFile);
        writer.write(file.getBytes());
        return randomFileName;
    }
}
