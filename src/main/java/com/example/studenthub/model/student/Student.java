package com.example.studenthub.model.student;

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
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "studentId")
public class Student {

    private UUID studentId;

    private String firstName;

    private String lastName;

    private String email;

    private Gender gender;

    public Student(String firstName, String lastName, String email, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }
}
