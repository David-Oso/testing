package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.dto.response.RegisterResponse;
import com.test.Testing.data.model.Student;

import java.util.List;

public interface StudentService {
    RegisterResponse registerStudent(RegisterStudentRequest registerStudentRequest);
    LoginResponse login(LoginRequest loginRequest);
    Student getStudentById(Long studentId);
    Student getStudentByEmail(String email);
    List<Student> getAllStudents();
    void deleteStudentById(Long studentId);
    void deleteAllStudents();
    Long getNumberOfStudents();
}
