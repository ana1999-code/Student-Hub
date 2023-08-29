package com.example.studenthub.service;

import com.example.studenthub.dao.StudentDao;
import com.example.studenthub.dao.StudentDataAccessService;
import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.student.StudentCourse;
import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentDto;
import com.example.studenthub.validation.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;

    private final EmailValidator emailValidator;

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
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

        studentDao.addStudent(student);
    }

    private void validateEmail(String email) {
        if (!emailValidator.test(email)){
            throw new ApiRequestException("Email %s is not valid".formatted(email));
        }

        if (studentDao.isEmailTaken(email)){
            throw new ApiRequestException("Email %s is already taken".formatted(email));
        }
    }

    public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        return studentDao.getAllCoursesForStudent(studentId);
    }

    public void deleteStudent(UUID studentId) {
        studentDao.deleteStudent(studentId);
    }
}
