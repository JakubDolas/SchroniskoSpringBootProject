package com.example.Projekt.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeePhotos")
public class EmployeePhotoController {

    @Autowired
    private EmployeePhotoRepository employeePhotoRepository;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getEmployeePhotoById(@PathVariable Long id) {
        return employeePhotoRepository.findById(id)
                .map(EmployeePhoto::getImageData)
                .orElseThrow(() -> new RuntimeException("Employee photo not found"));
    }
}
