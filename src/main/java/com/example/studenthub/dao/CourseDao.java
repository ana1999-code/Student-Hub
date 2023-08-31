package com.example.studenthub.dao;

import com.example.studenthub.model.course.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourses();

    int addCourse(Course course);

    boolean isNameTaken(String courseName);
}
