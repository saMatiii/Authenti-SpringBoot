package com.social.services;

import com.oracle.tools.packager.Log;
import com.social.dao.ImageRepository;
import com.social.entities.Image;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService {
    @Autowired
    ImageRepository imageRepository;

   Logger log= LoggerFactory.getLogger(ImageStorageService.class);
   private final Path rootLocation= Paths.get("/Users/mac/Desktop/test-copyImages");


        public void store(MultipartFile file){
            try {
                Files.copy(file.getInputStream(),this.rootLocation.resolve(file.getOriginalFilename()));
            }catch (IOException e) {
                   throw new RuntimeException(("Fail Uplodaing"));
             }
        }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public void saveImage(MultipartFile file){
        try {

              byte[] imageByts=file.getBytes();
              Image image= new Image("piName","some type",imageByts);
              imageRepository.save(image);

        } catch (IOException e) {
            e.printStackTrace();
            Log.info("Could not read or save image in Repo ");
            throw new RuntimeException("Could not Read or store image!");

        }
    }

}
