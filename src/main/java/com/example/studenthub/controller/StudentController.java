package com.example.studenthub.controller;

import com.example.studenthub.model.student.StudentCourse;
import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentDto;
import com.example.studenthub.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.of(Optional.of(students));
    }

    @PostMapping
    public void addStudent(@RequestBody @Valid StudentDto studentDto) {
        studentService.addStudent(studentDto);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") UUID studentId){
        studentService.deleteStudent(studentId);
    }

    @GetMapping("{studentId}/courses")
    public ResponseEntity<List<StudentCourse>> getAllCoursesForStudent(@PathVariable("studentId")UUID studentId){
        final List<StudentCourse> courses = studentService.getAllCoursesForStudent(studentId);
        return ResponseEntity.ok(courses);
    }
}
