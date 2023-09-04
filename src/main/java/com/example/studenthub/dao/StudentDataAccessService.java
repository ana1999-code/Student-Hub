package com.example.studenthub.dao;

import com.example.studenthub.model.course.Course;
import com.example.studenthub.model.student.StudentCourse;
import com.example.studenthub.model.student.Gender;
import com.example.studenthub.model.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class StudentDataAccessService implements StudentDao{

    private final JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents() {
        final String sql = "SELECT * FROM student";

        return jdbcTemplate.query(sql, mapStudentFromDb());
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
                "VALUES (?, ?, ?, ?, ?::gender)";

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

    public List<StudentCourse> getAllCoursesForStudent(UUID studentId) {
        final String sql = "SELECT sc.student_id, c.course_id, c.name, c.description, c.department, c.teacher_name, sc.start_date, sc.end_date, sc.grade " +
                "FROM student s " +
                "INNER JOIN student_course sc USING(student_id) " +
                "INNER JOIN course c ON c.course_id=sc.course_id " +
                "WHERE s.student_id = ?";

        return jdbcTemplate.query(sql, new Object[]{studentId}, mapCourseInfoFromDb());
    }

    private RowMapper<StudentCourse> mapCourseInfoFromDb() {
        return (rs, index) -> {
            final UUID studentId = UUID.fromString(rs.getString("student_id"));
            final UUID courseId = UUID.fromString(rs.getString("course_id"));
            final String name = rs.getString("name");
            final String description = rs.getString("description");
            final String department = rs.getString("department");
            final String teacherName = rs.getString("teacher_name");
            final LocalDate startDate = rs.getDate("start_date").toLocalDate();
            final LocalDate endDate = rs.getDate("end_date").toLocalDate();
            final Integer grade = Optional.ofNullable(rs.getString("grade"))
                    .map(Integer::parseInt)
                    .orElse(null);
            final Course course = Course.builder()
                    .courseId(courseId)
                    .name(name)
                    .department(department)
                    .description(description)
                    .teacherName(teacherName)
                    .build();

            return StudentCourse.builder()
                    .studentId(studentId)
                    .course(course)
                    .startDate(startDate)
                    .endDate(endDate)
                    .grade(grade)
                    .build();
        };
    }

    public void deleteStudent(UUID studentId) {
        final String sql = "DELETE FROM student" +
                " WHERE student_id = ?";

        jdbcTemplate.update(sql, studentId);
    }

    @Override
    public void addCourseForStudent(UUID studentId, StudentCourse studentCourse) {
        final String sql = "INSERT INTO student_course(" +
                "student_id, " +
                "course_id, " +
                "start_date, " +
                "end_date, " +
                "grade) " +
                "VALUES(?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                studentId,
                studentCourse.getCourse().getCourseId(),
                studentCourse.getStartDate(),
                studentCourse.getEndDate(),
                studentCourse.getGrade()
        );
    }
}
