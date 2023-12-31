package com.test.Testing.service.teacher;

import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class TeacherServiceImplTest {
    @Autowired TeacherService teacherService;
    private TeacherLoginRequest teacherLoginRequest;

    @BeforeEach
    void setUp() {
        teacherLoginRequest = new TeacherLoginRequest();
        teacherLoginRequest.setEmail("noreply@testing.com");
        teacherLoginRequest.setPassword("TeacherPassword");
        teacherLoginRequest.setIdentity("teacher101");
    }

    @Test
    void login() {
        LoginResponse response = teacherService.login(teacherLoginRequest);
        assertThat(response.getMessage()).isEqualTo("Authentication Successful");
        assertThat(response.isSuccess()).isEqualTo(true);
        assertThat(response.getJwtTokenResponse()).isNotNull();
    }

    @Test
    void getTeacherByEmail() {
        Teacher teacher = teacherService.getTeacherByEmail("noreply@testing.com");
        AppUser appUser = teacher.getAppUser();
        assertThat(appUser.getFirstName()).isEqualTo("teacher");
        assertThat(appUser.getLastName()).isEqualTo("teacher");
    }
}
