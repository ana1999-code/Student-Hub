package com.example.studenthub.dao;

import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentCourse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static com.example.studenthub.utils.TestUtils.ALEX;
import static com.example.studenthub.utils.TestUtils.JOHN;
import static com.example.studenthub.utils.TestUtils.JOHN_EMAIL;
import static com.example.studenthub.utils.TestUtils.MARIA;
import static com.example.studenthub.utils.TestUtils.UNIQUE_INDEX_OR_PRIMARY_KEY_VIOLATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Sql({"/schema.sql", "/data.sql"})
class StudentDataAccessServiceTest {

    private JdbcTemplate jdbcTemplate;

    private StudentDataAccessService studentDataAccessService;

    @Autowired
    public StudentDataAccessServiceTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        studentDataAccessService = new StudentDataAccessService(jdbcTemplate);
    }

    @Test
    void itShouldReturnAllStudents_WhenGetAllStudents() {
        final List<Student> actualResult = studentDataAccessService.getAllStudents();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult.containsAll(List.of(JOHN, MARIA))).isTrue();
    }

    @Test
    void itShouldAddNewStudents() {
        studentDataAccessService.addStudent(ALEX);

        final List<Student> actualResult = studentDataAccessService.getAllStudents();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult.contains(ALEX)).isTrue();
    }

    @Test
    void itShouldThrow_WhenAddStudentWithExistingEmail() {
        assertThatThrownBy(() -> studentDataAccessService.addStudent(JOHN))
                .hasMessageContaining(UNIQUE_INDEX_OR_PRIMARY_KEY_VIOLATION)
                .isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void itShouldReturnTrue_WhenIsEmailRTaken() {
        final boolean emailTaken = studentDataAccessService.isEmailTaken(JOHN_EMAIL);

        assertThat(emailTaken).isTrue();
    }

    @Test
    void itShouldReturnFalse_WhenIsNotEmailTaken() {
        final boolean emailTaken = studentDataAccessService.isEmailTaken("alexb@gmail.com");

        assertThat(emailTaken).isFalse();
    }

    @Test
    void itShouldReturnAllCourses_WhenGetAllCoursesForStudent() {
        final List<StudentCourse> allCoursesForStudent = studentDataAccessService
                .getAllCoursesForStudent(UUID.fromString("efcb4c83-fd53-4ad3-9abd-ea93b7f09ee1"));

        assertThat(allCoursesForStudent).hasSize(2);
    }

    @Test
    void itShouldDeleteStudent() {
        studentDataAccessService.deleteStudent(UUID.fromString("efcb4c83-fd53-4ad3-9abd-ea93b7f09ee1"));
        final List<Student> allStudents = studentDataAccessService.getAllStudents();

        assertThat(allStudents.contains(JOHN)).isFalse();
        assertThat(allStudents.contains(MARIA)).isTrue();
        assertThat(allStudents).hasSize(1);
    }
}
