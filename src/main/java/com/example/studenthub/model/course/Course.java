package com.example.studenthub.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "courseId")
@ToString
public class Course {

    private UUID courseId;

    private String name;

    private String description;

    private String department;

    private String teacherName;
}
