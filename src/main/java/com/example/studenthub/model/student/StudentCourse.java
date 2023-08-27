package com.example.studenthub.model.student;

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

    private UUID courseId;

    private String name;

    private String description;

    private String department;

    private LocalDate startDate;

    private LocalDate endDate;

    private String teacherName;

    private Integer grade;
}
