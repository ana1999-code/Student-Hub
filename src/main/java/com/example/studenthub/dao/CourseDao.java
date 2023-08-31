package com.example.studenthub.dao;

import com.example.studenthub.model.course.Course;

import java.util.List;
import java.util.UUID;

public interface CourseDao {

    List<Course> getAllCourses();

    int addCourse(Course course);

    boolean isNameTaken(String courseName);

    void deleteCourse(UUID courseId);
}
