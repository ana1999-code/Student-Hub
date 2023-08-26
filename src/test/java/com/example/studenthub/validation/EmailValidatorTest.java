package com.example.studenthub.validation;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmailValidatorTest {

    private final EmailValidator emailValidator = new EmailValidator();

    @Test
    void itShouldReturnTrueWhenValidateCorrectEmail() {
        String email = "johnsmith@mail.com";

        boolean isValid = emailValidator.test(email);
        assertThat(isValid).isTrue();
    }

    @Test
    void itShouldReturnFalseWhenValidateIncorrectEmail() {
        String email = "johnsmithmail.com";

        boolean isValid = emailValidator.test(email);
        assertThat(isValid).isFalse();
    }
}