package com.example.vktraineeshipback.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageService {
    public byte[] getImage(String pack,String path) throws IOException {
        return Files.readAllBytes(new File(Utils.IMAGE_PATH.getPath()+pack+"/"+path).toPath());
    }
}
