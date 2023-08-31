package com.example.studenthub.dao;

import com.example.studenthub.model.course.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static com.example.studenthub.utils.TestUtils.HTML_COURSE;
import static com.example.studenthub.utils.TestUtils.JAVA_COURSE;
import static com.example.studenthub.utils.TestUtils.JOHN;
import static com.example.studenthub.utils.TestUtils.UNIQUE_INDEX_OR_PRIMARY_KEY_VIOLATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void itShouldAddNewCourse() {
        int courseIsAdded = courseDataAccessService.addCourse(HTML_COURSE);
        final List<Course> actualResult = courseDataAccessService.getAllCourses();

        assertThat(courseIsAdded).isEqualTo(1);
        assertThat(actualResult.contains(HTML_COURSE)).isTrue();
    }

    @Test
    void itShouldThrow_WhenAddNewCourseWithExistingName() {
        assertThatThrownBy(() -> courseDataAccessService.addCourse(JAVA_COURSE))
                .hasMessageContaining(UNIQUE_INDEX_OR_PRIMARY_KEY_VIOLATION)
                .isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void itShouldDeleteCourse() {
        String courseId = "83c27df7-eda4-43ae-9b2c-69dc22553600";
        courseDataAccessService.deleteCourse(UUID.fromString(courseId));

        final List<Course> actualResult = courseDataAccessService.getAllCourses();

        assertThat(actualResult).hasSize(1);
    }
}