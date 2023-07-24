package com.test.Testing.service.teacher;

import com.test.Testing.data.dto.request.TeacherLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeacherServiceImplTest {
    @Autowired TeacherService teacherService;
    private TeacherLoginRequest teacherLoginRequest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void login() {
    }

    @Test
    void getTeacherByEmail() {
    }
}