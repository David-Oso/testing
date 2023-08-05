package com.test.Testing.config.appConfig;

import com.test.Testing.data.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TeacherConfig {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String identity;
}
