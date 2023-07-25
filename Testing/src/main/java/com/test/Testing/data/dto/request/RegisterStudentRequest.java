package com.test.Testing.data.dto.request;

import com.test.Testing.data.model.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.test.Testing.utilities.TestingUtils.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterStudentRequest {
    @NotNull(message = "field first name cannot be null")
    @NotEmpty(message = "field first name cannot be empty")
    @NotBlank(message = "field first name cannot be blank")
    @Pattern(regexp = NAME_REGEX, message = "name must be only letters starting with capital letters")
    private String firstName;

    @NotNull(message = "field last name cannot be null")
    @NotEmpty(message = "field last name cannot be empty")
    @NotBlank(message = "field last name cannot be blank")
    @Pattern(regexp = NAME_REGEX, message = "name must be only letters starting with capital letters")
    private String lastName;

    @NotNull(message = "field phone number cannot be null")
    @NotEmpty(message = "field phone number cannot be empty")
    @NotBlank(message = "field phone number cannot be blank")
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "enter a valid phone number")
    private String phoneNumber;

    @NotNull(message = "field email cannot be null")
    @NotEmpty(message = "field email cannot be empty")
    @NotBlank(message = "field email cannot be blank")
    @Email(regexp = EMAIL_REGEX, message = "enter a valid email address")
    private String email;

    @NotNull(message = "field password cannot be null")
    @NotEmpty(message = "field password cannot be empty")
    @NotBlank(message = "field password cannot be blank")
    @Pattern(regexp = PASSWORD_REGEX, message = "Password must " +
            "contain at least one capital letter, one small letter, a number and special character(@$!%*?&)")
    private String password;

    private Gender gender;
}
