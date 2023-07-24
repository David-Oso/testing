package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.model.Student;

import java.util.List;

public interface StudentService {
    String registerStudent(RegisterStudentRequest registerStudentRequest);
    String login(LoginRequest loginRequest);
    Student getStudentById(Long studentId);
    Student getStudentByEmail(String email);
    List<Student> getAllStudents();
//    void deleteAStudent(Long studentId);
    void deleteAllStudents();
    Long studentCount();
}
