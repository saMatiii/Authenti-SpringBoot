package com.social.services;

import org.slf4j.*;
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
   Logger log= LoggerFactory.getLogger(ImageStorageService.class);
   private final Path rootLocation= Paths.get("C:\\Users\\afethall\\Desktop\\image_uplods");


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
}
