package com.example.studenthub.controller;

import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.course.Course;
import com.example.studenthub.service.CourseService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Objects;

import static com.example.studenthub.utils.ObjectToJsonMapper.objectToJson;
import static com.example.studenthub.utils.TestUtils.JAVA_COURSE;
import static com.example.studenthub.utils.TestUtils.JAVA_COURSE_DTO;
import static com.example.studenthub.utils.TestUtils.JAVA_COURSE_ID;
import static com.example.studenthub.utils.TestUtils.NAME_IS_PRESENT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    public static final String COURSES_URI = "/api/v1/courses";

    @MockBean
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldReturnCourse_WhenGetAllCourses() throws Exception {
        final List<Course> courses = List.of(JAVA_COURSE);
        when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(get(COURSES_URI))
                .andExpect(status().isOk())
                .andExpect(content().json(Objects
                        .requireNonNull(objectToJson(courses))));

        verify(courseService).getAllCourses();
    }

    @Test
    void itShouldAddNewCourse() throws Exception {
        mockMvc.perform(post(COURSES_URI)
                        .contentType(APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JAVA_COURSE_DTO))))
                .andExpect(status().isOk());

        verify(courseService).addCourse(JAVA_COURSE_DTO);
    }

    @Test
    @Disabled
    void itShouldThrow_WhenAddCourseWithExistingName() throws Exception {
        doThrow(new ApiRequestException(NAME_IS_PRESENT_ERROR))
                .when(courseService).addCourse(JAVA_COURSE_DTO);

        MvcResult mvcResult = mockMvc.perform(post(COURSES_URI)
                        .contentType(APPLICATION_JSON)
                        .content(Objects.requireNonNull(objectToJson(JAVA_COURSE_DTO))))
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResult = mvcResult.getResponse().getContentAsString();

        assertThat(actualResult.contains(NAME_IS_PRESENT_ERROR)).isTrue();
        verify(courseService).addCourse(JAVA_COURSE_DTO);
    }

    @Test
    void itShouldDeleteCourse() throws Exception {
        mockMvc.perform(delete(COURSES_URI + "/{courseId}", JAVA_COURSE_ID))
                .andExpect(status().isOk());

        verify(courseService).deleteCourse(JAVA_COURSE_ID);
    }
}