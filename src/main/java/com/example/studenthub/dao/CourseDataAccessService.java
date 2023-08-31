package com.example.studenthub.dao;

import com.example.studenthub.model.course.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourseDataAccessService implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Course> getAllCourses() {
        final String sql = "SELECT * FROM course";

        return jdbcTemplate.query(sql, mapCourseFromDb());
    }

    private static RowMapper<Course> mapCourseFromDb() {
        return (rs, index) -> {
            final UUID courseId = UUID.fromString(rs.getString("course_id"));
            final String name = rs.getString("name");
            final String description = rs.getString("description");
            final String department = rs.getString("department");
            final String teacherName = rs.getString("teacher_name");

            return Course.builder()
                    .courseId(courseId)
                    .name(name)
                    .department(department)
                    .description(description)
                    .teacherName(teacherName)
                    .build();
        };
    }
}
