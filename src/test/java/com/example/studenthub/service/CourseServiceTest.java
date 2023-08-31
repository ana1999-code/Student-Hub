package com.example.studenthub.service;

import com.example.studenthub.dao.CourseDao;
import com.example.studenthub.model.course.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.studenthub.utils.TestUtils.JAVA_COURSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseDao courseDao;

    @Test
    void itShouldReturnAllCourses_WhenGetAllCourses() {
        final List<Course> expectedResult = List.of(JAVA_COURSE);
        when(courseDao.getAllCourses()).thenReturn(expectedResult);

        final List<Course> actualResult = courseService.getAllCourses();

        assertThat(actualResult.containsAll(expectedResult)).isTrue();
        verify(courseDao).getAllCourses();
    }
}