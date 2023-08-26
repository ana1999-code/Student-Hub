package com.example.studenthub.dao;

import com.example.studenthub.model.student.Gender;
import com.example.studenthub.model.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents() {
        final String sql = "SELECT * FROM student";

        List<Student> students = jdbcTemplate.query(sql, mapStudentFromDb());

        return students;
    }

    private static RowMapper<Student> mapStudentFromDb() {
        return (rs, rowNum) -> {
            final UUID studentId = UUID.fromString(rs.getString("student_id"));
            final String firstName = rs.getString("first_name");
            final String lastName = rs.getString("last_name");
            final String email = rs.getString("email");
            final Gender gender = Gender.valueOf(rs.getString("gender").toUpperCase());

            return Student.builder()
                    .studentId(studentId)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .gender(gender)
                    .build();
        };
    }

    public int addStudent(Student student) {
        final String sql = "INSERT INTO student(" +
                "student_id, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "gender)" +
                "VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()
        );
    }

    public boolean isEmailTaken(String email) {
        final String sql = "SELECT EXISTS( " +
                "SELECT 1 " +
                "FROM student " +
                "WHERE email = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{email},
                (rs, index) -> rs.getBoolean(1));
    }
}
