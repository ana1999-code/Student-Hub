package com.example.studenthub.utils;

import com.example.studenthub.model.course.Course;
import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentCourse;
import com.example.studenthub.model.student.StudentDto;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static com.example.studenthub.model.student.Gender.FEMALE;
import static com.example.studenthub.model.student.Gender.MALE;

public class TestUtils {

    public static final UUID JOHN_ID = UUID.randomUUID();

    public static final String JOHN_FIRST_NAME = "John";

    public static final String JOHN_LAST_NAME = "Smith";

    public static final String JOHN_EMAIL = "jones0@mail.com";

    public static final StudentDto JOHN_DTO = new StudentDto(JOHN_FIRST_NAME, JOHN_LAST_NAME, JOHN_EMAIL, MALE);

    public static final Student JOHN = new Student(JOHN_ID, JOHN_FIRST_NAME, JOHN_LAST_NAME, JOHN_EMAIL, MALE);

    public static final Student MARIA = new Student(UUID.randomUUID(), "Maria", "Forest", "mariaf1@mail.com", FEMALE);

    public static final Student ALEX = new Student(UUID.randomUUID(), "Alex", "Bush", "alexb2@mail.com", MALE);

    public static final List<Student> STUDENT_LIST = List.of(JOHN, MARIA);

    public static final List<StudentCourse> COURSES = List.of(
            new StudentCourse(JOHN_ID, UUID.randomUUID(), "Java", "Java", "IT", LocalDate.of(2023, Month.APRIL, 2), LocalDate.of(2024, Month.AUGUST, 2), "Maria", 8),
            new StudentCourse(JOHN_ID, UUID.randomUUID(), "SQL", "SQL", "IT", LocalDate.of(2023, Month.APRIL, 2), LocalDate.of(2024, Month.AUGUST, 2), "Mike", 6),
            new StudentCourse(JOHN_ID, UUID.randomUUID(), "Spring", "Spring", "IT", LocalDate.of(2023, Month.APRIL, 2), LocalDate.of(2024, Month.AUGUST, 2), "Joly", 8)
    );

    public static final String INVALID_EMAIL = "jones.com";

    public static final StudentDto JOHN_WITH_INVALID_EMAIL = new StudentDto(JOHN_FIRST_NAME, JOHN_LAST_NAME, INVALID_EMAIL, MALE);

    public static final StudentDto JOHN_WITH_NULL_FIRST_NAME = new StudentDto(null, JOHN_LAST_NAME, INVALID_EMAIL, MALE);

    public static final StudentDto JOHN_WITH_NULL_LAST_NAME = new StudentDto(JOHN_FIRST_NAME, null, INVALID_EMAIL, MALE);

    public static final StudentDto JOHN_WITH_NULL_EMAIL = new StudentDto(JOHN_FIRST_NAME, JOHN_LAST_NAME, null, MALE);

    public static final StudentDto JOHN_WITH_NULL_GENDER = new StudentDto(JOHN_FIRST_NAME, JOHN_LAST_NAME, INVALID_EMAIL, null);

    public static final String INVALID_EMAIL_ERROR = "Email %s is not valid".formatted(INVALID_EMAIL);

    public static final String EMAIL_IS_TAKEN_ERROR = "Email %s is already taken".formatted(JOHN_DTO.getEmail());

    public static final String INVALID_REQUEST_CONTENT = "Invalid request content.";

    public static final UUID JAVA_COURSE_ID = UUID.randomUUID();

    public static final String JAVA = "JAVA";

    public static final Course JAVA_COURSE = new Course(JAVA_COURSE_ID, JAVA, "JAVA", "IT", null);
}
