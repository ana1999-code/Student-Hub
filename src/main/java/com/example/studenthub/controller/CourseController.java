package com.example.studenthub.controller;

import com.example.studenthub.model.course.Course;
import com.example.studenthub.model.course.CourseDto;
import com.example.studenthub.service.CourseService;
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
import java.util.UUID;

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

    @PostMapping
    public void addCourse(@RequestBody @Valid CourseDto courseDto){
        courseService.addCourse(courseDto);
    }

    @DeleteMapping("{courseId}")
    public void deleteCourse(@PathVariable("courseId")UUID courseId){
        courseService.deleteCourse(courseId);
    }
}
