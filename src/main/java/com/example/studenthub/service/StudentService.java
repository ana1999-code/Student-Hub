package com.example.studenthub.service;

import com.example.studenthub.dao.StudentDataAccessService;
import com.example.studenthub.model.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;

    public List<Student> getAllStudents() {
        return studentDataAccessService.getAllStudents();
    }
}
