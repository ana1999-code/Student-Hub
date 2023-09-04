package com.example.studenthub.model.student;

import com.example.studenthub.model.course.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentCourse {

    private UUID studentId;

    private Course course;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer grade;
}
