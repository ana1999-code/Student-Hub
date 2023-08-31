package com.example.studenthub.dao;

import com.example.studenthub.model.course.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Sql({"/schema.sql", "/data.sql"})
class CourseDataAccessServiceTest {

    private JdbcTemplate jdbcTemplate;

    private CourseDataAccessService courseDataAccessService;

    @Autowired
    public CourseDataAccessServiceTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        courseDataAccessService = new CourseDataAccessService(jdbcTemplate);
    }

    @Test
    void itShouldReturnCourses_WhenGetAllCourses() {
        final List<Course> courses = courseDataAccessService.getAllCourses();

        assertThat(courses).hasSize(2);
    }
}