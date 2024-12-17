package com.example.Projekt.AnimalsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhotoById(@PathVariable Long id) {
        return photoRepository.findById(id)
                .map(Photo::getImageData)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
    }
}
