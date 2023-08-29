package com.example.studenthub.service;

import com.example.studenthub.dao.StudentDataAccessService;
import com.example.studenthub.exception.ApiRequestException;
import com.example.studenthub.model.student.Student;
import com.example.studenthub.model.student.StudentCourse;
import com.example.studenthub.validation.EmailValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.studenthub.utils.TestUtils.COURSES;
import static com.example.studenthub.utils.TestUtils.EMAIL_IS_TAKEN_ERROR;
import static com.example.studenthub.utils.TestUtils.INVALID_EMAIL;
import static com.example.studenthub.utils.TestUtils.INVALID_EMAIL_ERROR;
import static com.example.studenthub.utils.TestUtils.JOHN;
import static com.example.studenthub.utils.TestUtils.JOHN_DTO;
import static com.example.studenthub.utils.TestUtils.JOHN_EMAIL;
import static com.example.studenthub.utils.TestUtils.JOHN_ID;
import static com.example.studenthub.utils.TestUtils.JOHN_WITH_INVALID_EMAIL;
import static com.example.studenthub.utils.TestUtils.STUDENT_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentDataAccessService studentDataAccessService;

    @Mock
    private EmailValidator emailValidator;

    @Captor
    private ArgumentCaptor<Student> studentArgumentCaptor;

    @Test
    void itShouldReturnAllStudents_WhenGetAllStudents() {
        when(studentDataAccessService.getAllStudents()).thenReturn(STUDENT_LIST);

        final List<Student> actualResult = studentService.getAllStudents();

        assertThat(actualResult).isEqualTo(STUDENT_LIST);
        verify(studentDataAccessService).getAllStudents();
    }

    @Test
    void itShouldAddNewStudent() {
        when(emailValidator.test(JOHN_EMAIL)).thenReturn(true);
        studentService.addStudent(JOHN_DTO);

        verify(studentDataAccessService).addStudent(studentArgumentCaptor.capture());
        final Student actualResult = studentArgumentCaptor.getValue();

        assertThat(actualResult).isEqualTo(JOHN);
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithInvalidEmail() {
        when(emailValidator.test(INVALID_EMAIL)).thenReturn(false);

        assertThatThrownBy(() -> studentService.addStudent(JOHN_WITH_INVALID_EMAIL))
                .hasMessage(INVALID_EMAIL_ERROR)
                .isInstanceOf(ApiRequestException.class);

        verify(studentDataAccessService, times(0)).addStudent(any(Student.class));
    }

    @Test
    void itShouldThrow_WhenAddNewStudentWithExistingEmail() {
        when(emailValidator.test(JOHN_EMAIL)).thenReturn(true);
        when(studentDataAccessService.isEmailTaken(JOHN_EMAIL)).thenReturn(true);

        assertThatThrownBy(() -> studentService.addStudent(JOHN_DTO))
                .hasMessage(EMAIL_IS_TAKEN_ERROR)
                .isInstanceOf(ApiRequestException.class);

        verify(studentDataAccessService).isEmailTaken(JOHN_EMAIL);
        verify(studentDataAccessService, times(0)).addStudent(any(Student.class));
    }

    @Test
    void itShouldReturnAllCourses_WhenGetAllCoursesForStudent() {
        when(studentDataAccessService.getAllCoursesForStudent(JOHN_ID)).thenReturn(COURSES);

        final List<StudentCourse> actualResult = studentService.getAllCoursesForStudent(JOHN_ID);

        assertThat(actualResult).isEqualTo(COURSES);
        verify(studentDataAccessService).getAllCoursesForStudent(JOHN_ID);
    }

    @Test
    void itShouldDeleteStudent() {
        doNothing().when(studentDataAccessService).deleteStudent(JOHN_ID);

        assertThatNoException().isThrownBy(() -> studentService.deleteStudent(JOHN_ID));
        verify(studentDataAccessService).deleteStudent(JOHN_ID);
    }
}