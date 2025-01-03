package com.example.Projekt.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeePhotoService {

    @Autowired
    private EmployeePhotoRepository employeePhotoRepository;

    public void savePhoto(EmployeePhoto employeePhoto) {
        employeePhotoRepository.save(employeePhoto);
    }
}

