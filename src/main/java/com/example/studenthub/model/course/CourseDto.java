package com.example.studenthub.model.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CourseDto {

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("description")
    @NotBlank
    private String description;

    @JsonProperty("department")
    @NotBlank
    private String department;

    @JsonProperty("teacherName")
    private String teacherName;
}
