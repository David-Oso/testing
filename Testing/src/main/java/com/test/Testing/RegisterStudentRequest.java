package com.test.Testing;

import com.test.Testing.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterStudentRequest {
    private String firstName;
    private String lastName;
    private Gender gender;
}
