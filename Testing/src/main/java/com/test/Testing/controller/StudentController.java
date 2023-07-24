package com.test.Testing.controller;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.service.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/testing/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("register")
    public ResponseEntity<?> registerStudent(RegisterStudentRequest registerStudentRequest){
        String response = studentService.registerStudent(registerStudentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    public ResponseEntity<?> login(LoginRequest loginRequest){
        String response = studentService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


//    String login(LoginRequest loginRequest);
//    Student getStudentById(Long studentId);
//    Student getStudentByEmail(String email);
//    List<Student> getAllStudents();
//    void deleteAStudent(Long studentId);
//    void deleteAllStudents();
//    Long studentCount();
}
