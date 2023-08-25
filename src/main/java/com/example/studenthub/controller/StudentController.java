package com.example.studenthub.controller;

import com.example.studenthub.model.student.Gender;
import com.example.studenthub.model.student.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = List.of(
                new Student(UUID.randomUUID(), "amy", "smith", "amy@mail.com", Gender.FEMALE),
                new Student(UUID.randomUUID(), "john", "more", "jonh@mail.com", Gender.MALE)
        );
        return ResponseEntity.of(Optional.of(students));
    }
}
