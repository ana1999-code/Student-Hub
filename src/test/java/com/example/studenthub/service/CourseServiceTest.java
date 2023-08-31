package com.example.studenthub.service;

import com.example.studenthub.dao.CourseDao;
import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.course.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.studenthub.utils.TestUtils.JAVA;
import static com.example.studenthub.utils.TestUtils.JAVA_COURSE;
import static com.example.studenthub.utils.TestUtils.JAVA_COURSE_DTO;
import static com.example.studenthub.utils.TestUtils.NAME_IS_PRESENT_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseDao courseDao;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;

    @Test
    void itShouldReturnAllCourses_WhenGetAllCourses() {
        final List<Course> expectedResult = List.of(JAVA_COURSE);
        when(courseDao.getAllCourses()).thenReturn(expectedResult);

        final List<Course> actualResult = courseService.getAllCourses();

        assertThat(actualResult.containsAll(expectedResult)).isTrue();
        verify(courseDao).getAllCourses();
    }

    @Test
    void itShouldAddNewCourse() {
        when(courseDao.isNameTaken(JAVA)).thenReturn(false);

        courseService.addCourse(JAVA_COURSE_DTO);

        verify(courseDao).addCourse(courseArgumentCaptor.capture());
        final Course actualResult = courseArgumentCaptor.getValue();

        assertThat(actualResult).isEqualTo(JAVA_COURSE);
    }

    @Test
    void itShouldThrow_WhenAddNewCourseWithExistingName() {
        when(courseDao.isNameTaken(JAVA)).thenReturn(true);

        assertThatThrownBy(() -> courseService.addCourse(JAVA_COURSE_DTO))
                .isInstanceOf(ApiRequestException.class)
                .hasMessage(NAME_IS_PRESENT_ERROR);
    }
}