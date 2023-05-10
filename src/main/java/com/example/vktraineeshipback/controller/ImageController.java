package com.example.vktraineeshipback.controller;

import com.example.vktraineeshipback.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping("{pack}/{path}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("package")String pack, @PathVariable("path")String path) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageService.getImage(pack,path));
    }
}
