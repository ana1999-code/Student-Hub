package com.example.studenthub.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    @ParameterizedTest
    @CsvSource({
            "johns@gmail.com",
            "johns123@gmail.com",
            "JONS@gmail.com",
            "Jonnes@gmail.com",
            "johns@gmail.md",
            "johns@gmail.softer",
            "jones_123@gmail.com"
    })
    void itShouldValidateCorrectEmail(String email) {
        boolean isValid = emailValidator.test(email);
        assertThat(isValid).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "johns@gmail.c",
            "johns123@gmail.education",
            "JONSgmail.com",
            "Jonnes@gmail",
            "johns!@gmail.md",
            "johns\"@gmail.softer",
            "jones_123"
    })
    void itShouldNotValidateIncorrectEmail(String email) {
        boolean isValid = emailValidator.test(email);
        assertThat(isValid).isFalse();
    }
}