package com.example.studenthub.model.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class StudentDto {

    @JsonProperty("firstName")
    @NotBlank
    private final String firstName;

    @JsonProperty("lastName")
    @NotBlank
    private final String lastName;

    @JsonProperty("email")
    @NotBlank
    private final String email;

    @JsonProperty("gender")
    @NotNull
    private final Gender gender;
}
