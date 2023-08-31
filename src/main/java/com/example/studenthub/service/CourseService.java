package com.example.studenthub.service;

import com.example.studenthub.dao.CourseDao;
import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.course.Course;
import com.example.studenthub.model.course.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseDao courseDao;

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void addCourse(CourseDto courseDto) {
        final String courseName = courseDto.getName();

        validateCourse(courseName);

        final Course course = Course.builder()
                .courseId(UUID.randomUUID())
                .name(courseName)
                .description(courseDto.getDescription())
                .department(courseDto.getDepartment())
                .teacherName(courseDto.getTeacherName())
                .build();

        courseDao.addCourse(course);
    }

    private void validateCourse(String courseName) {
        if (courseDao.isNameTaken(courseName)){
            throw new ApiRequestException("%s is already present".formatted(courseName));
        }
    }

    public void deleteCourse(UUID courseId) {
        courseDao.deleteCourse(courseId);
    }
}
