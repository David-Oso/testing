package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.model.Gender;
import com.test.Testing.data.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentServiceImplTest {
    @Autowired StudentService studentService;
    private RegisterStudentRequest registerStudentRequest1;
    private RegisterStudentRequest registerStudentRequest2;
    private LoginRequest loginRequest;

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

        loginRequest = new LoginRequest();
        loginRequest.setEmail("bola@gmail.com");
        loginRequest.setPassword("Password");
    }

    @Test
    void registerStudent() {
        String response1 = studentService.registerStudent(registerStudentRequest1);
        assertThat(response1).isEqualTo("Student Registration Successful");

        String response2 = studentService.registerStudent(registerStudentRequest2);
        assertThat(response2).isEqualTo("Student Registration Successful");
    }

    @Test
    void login() {
        String response = studentService.login(loginRequest);
        assertThat(response).isEqualTo("Authentication Successful");
    }

    @Test
    void getStudentById() {
        Student student = studentService.getStudentById(1L);
        assertThat(student.getAppUser().getFirstName()).isEqualTo(registerStudentRequest1.getFirstName());
    }

    @Test
    void getStudentByEmailTest(){
        Student student = studentService.getStudentByEmail("ayo@gmail.com");
        assertThat(student.getAppUser().getEmail()).isEqualTo(registerStudentRequest2.getEmail());
    }

    @Test
    void getAllStudents() {
        Long numberOfStudents = 2L;
        assertThat(studentService.studentCount()).isEqualTo(numberOfStudents);
    }

    @Test
    void deleteAStudent() {
        studentService.deleteAStudent(2L);
        Long numberOfStudents = 1L;
        assertThat(studentService.studentCount()).isEqualTo(numberOfStudents);
    }

    @Test
    void deleteAllStudents() {
        studentService.deleteAllStudents();
        Long numberOfStudents = 0L;
        assertThat(studentService.studentCount()).isEqualTo(numberOfStudents);
    }
}
