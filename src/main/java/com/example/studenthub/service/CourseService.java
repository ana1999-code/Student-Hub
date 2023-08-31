package com.example.studenthub.service;

import com.example.studenthub.dao.CourseDao;
import com.example.studenthub.model.course.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseDao courseDao;

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }
}
