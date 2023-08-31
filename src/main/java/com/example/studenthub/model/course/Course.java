package com.example.studenthub.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    private UUID courseId;

    private String name;

    private String description;

    private String department;

    private String teacherName;
}
