package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceImplTest {
    @Autowired StudentService studentService;
    private RegisterStudentRequest registerStudentRequest1;
    private RegisterStudentRequest registerStudentRequest2;

    @BeforeEach
    void setUp() {
        registerStudentRequest1 = new RegisterStudentRequest();
        registerStudentRequest1.setFirstName("Bola");
        registerStudentRequest1.setLastName("Remi");
        registerStudentRequest1.setPhoneNumber("09040875678");
        registerStudentRequest1.setEmail("bola@gmail.com");
        registerStudentRequest1.setPassword("Password");
        registerStudentRequest1.setGender(Gender.FEMALE);

        registerStudentRequest2 = new RegisterStudentRequest();
        registerStudentRequest2.setFirstName("Ayo");
        registerStudentRequest2.setLastName("Tolani");
        registerStudentRequest2.setPhoneNumber("08030500848");
        registerStudentRequest2.setEmail("ayo@gmail.com");
        registerStudentRequest2.setPassword("Password");
        registerStudentRequest2.setGender(Gender.MALE);
    }

    @Test
    void registerStudent() {
    }

    @Test
    void login() {
    }

    @Test
    void writeToAStudent() {
    }

    @Test
    void getStudentById() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void deleteAStudent() {
    }

    @Test
    void deleteAllStudents() {
    }
}