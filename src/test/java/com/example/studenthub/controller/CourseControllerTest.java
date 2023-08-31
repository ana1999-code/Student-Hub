package com.example.studenthub.controller;

import com.example.studenthub.model.course.Course;
import com.example.studenthub.service.CourseService;
import com.example.studenthub.utils.ObjectToJsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;

import static com.example.studenthub.utils.TestUtils.JAVA_COURSE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                        .requireNonNull(ObjectToJsonMapper.objectToJson(courses))));

        verify(courseService).getAllCourses();
    }
}