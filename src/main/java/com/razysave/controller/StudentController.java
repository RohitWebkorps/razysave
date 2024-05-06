package com.razysave.controller;

import com.razysave.entity.property.Student;
import com.razysave.repository.property.StudentRepository;
import com.razysave.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/")
    public ResponseEntity<Object> addBuilding(@RequestBody Student student) {
        //buildingService.addBuilding(building);
        studentRepository.save(student);
        return ResponseHandler.generateResponse("Added succesfully", HttpStatus.CREATED, student);
    }
}
