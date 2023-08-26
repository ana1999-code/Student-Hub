package com.example.studenthub.service;

import com.example.studenthub.dao.StudentDataAccessService;
import com.example.studenthub.model.student.StudentDto;
import com.example.studenthub.model.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;

    public List<Student> getAllStudents() {
        return studentDataAccessService.getAllStudents();
    }

    public void addStudent(StudentDto studentDto) {
        Optional<Student> studentWithTakenEmail = getAllStudents().stream()
                .filter(student -> student.getEmail().equals(studentDto.getEmail()))
                .findFirst();

        Student student = Student.builder()
                .studentId(UUID.randomUUID())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .gender(studentDto.getGender())
                .build();

        studentDataAccessService.addStudent(student);
    }
}
