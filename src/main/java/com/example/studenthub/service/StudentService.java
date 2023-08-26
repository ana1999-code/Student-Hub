package com.example.studenthub.service;

import com.example.studenthub.dao.StudentDataAccessService;
import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentDto;
import com.example.studenthub.validation.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDataAccessService studentDataAccessService;

    private final EmailValidator emailValidator;

    public List<Student> getAllStudents() {
        return studentDataAccessService.getAllStudents();
    }

    public void addStudent(StudentDto studentDto) {
        final String email = studentDto.getEmail();

        validateEmail(email);

        Student student = Student.builder()
                .studentId(UUID.randomUUID())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(email)
                .gender(studentDto.getGender())
                .build();

        studentDataAccessService.addStudent(student);
    }

    private void validateEmail(String email) {
        if (!emailValidator.test(email)){
            throw new ApiRequestException("Email %s is not valid".formatted(email));
        }

        if (studentDataAccessService.isEmailTaken(email)){
            throw new ApiRequestException("Email %s is already taken".formatted(email));
        }
    }
}
