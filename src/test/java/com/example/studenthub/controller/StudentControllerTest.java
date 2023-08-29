package com.example.studenthub.controller;

import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static com.example.studenthub.utils.ObjectToJsonMapper.objectToJson;
import static com.example.studenthub.utils.TestUtils.COURSES;
import static com.example.studenthub.utils.TestUtils.EMAIL_IS_TAKEN_ERROR;
import static com.example.studenthub.utils.TestUtils.INVALID_EMAIL;
import static com.example.studenthub.utils.TestUtils.INVALID_EMAIL_ERROR;
import static com.example.studenthub.utils.TestUtils.INVALID_REQUEST_CONTENT;
import static com.example.studenthub.utils.TestUtils.JOHN;
import static com.example.studenthub.utils.TestUtils.JOHN_DTO;
import static com.example.studenthub.utils.TestUtils.JOHN_ID;
import static com.example.studenthub.utils.TestUtils.JOHN_WITH_INVALID_EMAIL;
import static com.example.studenthub.utils.TestUtils.JOHN_WITH_NULL_EMAIL;
import static com.example.studenthub.utils.TestUtils.JOHN_WITH_NULL_FIRST_NAME;
import static com.example.studenthub.utils.TestUtils.JOHN_WITH_NULL_GENDER;
import static com.example.studenthub.utils.TestUtils.JOHN_WITH_NULL_LAST_NAME;
import static com.example.studenthub.utils.TestUtils.STUDENT_LIST;
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

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldReturnStudents_WhenGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(STUDENT_LIST);

        MvcResult mvcResult = mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().json(Objects.requireNonNull(objectToJson(STUDENT_LIST))))
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = objectToJson(STUDENT_LIST);

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(studentService).getAllStudents();
    }

    @Test
    void itShouldAddNewStudent() throws Exception {
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN_DTO))))
                .andExpect(status().isOk());

        verify(studentService).addStudent(JOHN_DTO);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithInvalidEmail() throws Exception {
        doThrow(new ApiRequestException(INVALID_EMAIL_ERROR)).when(studentService).addStudent(JOHN_WITH_INVALID_EMAIL);

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN_WITH_INVALID_EMAIL))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertThat(actualResult.contains(INVALID_EMAIL_ERROR)).isTrue();
        verify(studentService).addStudent(JOHN_WITH_INVALID_EMAIL);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithExistingEmail() throws Exception {
        doThrow(new ApiRequestException(EMAIL_IS_TAKEN_ERROR))
                .when(studentService).addStudent(JOHN_DTO);

        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();

        assertThat(actualResult.contains(EMAIL_IS_TAKEN_ERROR)).isTrue();
        verify(studentService).addStudent(JOHN_DTO);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithNullGender() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN_WITH_NULL_GENDER))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();

        assertThat(actualResult).isEqualTo(INVALID_REQUEST_CONTENT);
        verify(studentService, times(0)).addStudent(JOHN_WITH_NULL_GENDER);
    }


    @Test
    void itShouldThrow_WhenAddNewStudentWithBlankFirstName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN_WITH_NULL_FIRST_NAME))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();

        assertThat(actualResult).isEqualTo(INVALID_REQUEST_CONTENT);
        verify(studentService, times(0)).addStudent(JOHN_WITH_NULL_FIRST_NAME);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithBlankLastName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN_WITH_NULL_LAST_NAME))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();

        assertThat(actualResult).isEqualTo(INVALID_REQUEST_CONTENT);
        verify(studentService, times(0)).addStudent(JOHN_WITH_NULL_LAST_NAME);
    }


    @Test
    void itShouldThrow_WhenAddNewStudentWithBlankEmail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JOHN_WITH_NULL_EMAIL))))
                .andExpect(status().isBadRequest())
                .andReturn();

        String actualResult = mvcResult.getResponse().getErrorMessage();

        assertThat(actualResult).isEqualTo(INVALID_REQUEST_CONTENT);
        verify(studentService, times(0)).addStudent(JOHN_WITH_NULL_EMAIL);
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
}