package com.example.studenthub.controller;

import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.student.Gender;
import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentCourse;
import com.example.studenthub.model.student.StudentDto;
import com.example.studenthub.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    public static final UUID JOHN_ID = UUID.randomUUID();
    static final Student JOHN = new Student(JOHN_ID, "John", "Smith", "jones0@mail.com", Gender.MALE);
    static final Student MARIA = new Student(UUID.randomUUID(), "Maria", "Forest", "mariaf1@mail.com", Gender.FEMALE);
    static final List<StudentCourse> COURSES = List.of(
            new StudentCourse(JOHN_ID, UUID.randomUUID(), "Java", "Java", "IT", LocalDate.of(2023, Month.APRIL, 2), LocalDate.of(2024, Month.AUGUST, 2), "Maria", 8),
            new StudentCourse(JOHN_ID, UUID.randomUUID(), "SQL", "SQL", "IT", LocalDate.of(2023, Month.APRIL, 2), LocalDate.of(2024, Month.AUGUST, 2), "Mike", 6),
            new StudentCourse(JOHN_ID, UUID.randomUUID(), "Spring", "Spring", "IT", LocalDate.of(2023, Month.APRIL, 2), LocalDate.of(2024, Month.AUGUST, 2), "Joly", 8)
    );

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldReturnStudents_WhenGetAllStudents() throws Exception {
        final List<Student> studentList = List.of(JOHN, MARIA);

        when(studentService.getAllStudents()).thenReturn(studentList);

        MvcResult mvcResult = mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().json(Objects.requireNonNull(objectToJson(studentList))))
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectToJson(studentList);

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService).getAllStudents();
    }

    @Test
    void itShouldAddNewStudent() throws Exception {
        final StudentDto john = new StudentDto(JOHN.getFirstName(), JOHN.getLastName(), JOHN.getEmail(), JOHN.getGender());

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isOk());

        verify(studentService).addStudent(john);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithInvalidEmail() throws Exception {
        final StudentDto john = new StudentDto(JOHN.getFirstName(), JOHN.getLastName(), "jones.com", JOHN.getGender());

        doThrow(new ApiRequestException("Email %s is not valid".formatted(john.getEmail()))).when(studentService).addStudent(john);

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertThat(actualResult.contains("Email %s is not valid".formatted(john.getEmail()))).isTrue();
        verify(studentService).addStudent(john);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithExistingEmail() throws Exception {
        final StudentDto john = new StudentDto(JOHN.getFirstName(), JOHN.getLastName(), JOHN.getEmail(), JOHN.getGender());

        doThrow(new ApiRequestException("Email %s is already taken".formatted(john.getEmail()))).when(studentService).addStudent(john);

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertThat(actualResult.contains("Email %s is already taken".formatted(john.getEmail()))).isTrue();
        verify(studentService).addStudent(john);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithNullGender() throws Exception {
        final StudentDto john = new StudentDto(null, JOHN.getLastName(), JOHN.getEmail(), null);

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();
        String expectedResult = "Invalid request content.";

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService, times(0)).addStudent(john);
    }


    @Test
    void itShouldThrow_WhenAddNewStudentWithBlankFirstName() throws Exception {
        final StudentDto john = new StudentDto(null, JOHN.getLastName(), JOHN.getEmail(), JOHN.getGender());

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();
        String expectedResult = "Invalid request content.";

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService, times(0)).addStudent(john);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithBlankLastName() throws Exception {
        final StudentDto john = new StudentDto(JOHN.getFirstName(), null, JOHN.getEmail(), JOHN.getGender());

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();
        String expectedResult = "Invalid request content.";

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService, times(0)).addStudent(john);
    }


    @Test
    void itShouldThrow_WhenAddNewStudentWithBlankEmail() throws Exception {
        final StudentDto john = new StudentDto(JOHN.getFirstName(), JOHN.getLastName(), null, JOHN.getGender());

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(john))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();
        String expectedResult = "Invalid request content.";

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService, times(0)).addStudent(john);
    }

    @Test
    void itShouldReturnAllCoursesForStudent() throws Exception {
        when(studentService.getAllCoursesForStudent(JOHN_ID)).thenReturn(COURSES);


        MvcResult mvcResult = mockMvc.perform(get("/students/{studentId}/courses", JOHN_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(Objects.requireNonNull(objectToJson(COURSES))))
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectToJson(COURSES);

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService).getAllCoursesForStudent(JOHN_ID);
    }

    @Test
    void itShouldDeleteStudent() throws Exception {
        mockMvc.perform(delete("/students/{studentId}", JOHN_ID))
                .andExpect(status().isOk())
                .andReturn();

        verify(studentService).deleteStudent(JOHN_ID);
    }

    private String objectToJson(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }
}