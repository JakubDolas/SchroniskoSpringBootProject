package com.example.Projekt.AnimalsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }
}
