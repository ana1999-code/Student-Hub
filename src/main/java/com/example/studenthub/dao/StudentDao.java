package com.example.studenthub.dao;

import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentCourse;

import java.util.List;
import java.util.UUID;

public interface StudentDao {

    List<Student> getAllStudents();

    int addStudent(Student student);

    boolean isEmailTaken(String email);

    List<StudentCourse> getAllCoursesForStudent(UUID studentId);

    void deleteStudent(UUID studentId);
}
