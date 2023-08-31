package com.example.studenthub.controller;

import com.example.studenthub.model.course.Course;
import com.example.studenthub.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        final List<Course> allCourses = courseService.getAllCourses();
        return ResponseEntity.ok(allCourses);
    }
}
