package com.test.Testing.controller;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.model.Student;
import com.test.Testing.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/testing/student/")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("register")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody RegisterStudentRequest registerStudentRequest){
        String response = studentService.registerStudent(registerStudentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        String response = studentService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getStudentById(@Valid @PathVariable Long id){
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("get/email")
    public ResponseEntity<?> getStudentByEmail(@Valid @RequestParam String email){
        Student student = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(student);
    }

    @GetMapping("all/get")
    public ResponseEntity<?> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteStudentById(@Valid@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted.");
    }
    @DeleteMapping("all/delete")
    public ResponseEntity<?> deleteAllStudents(){
        studentService.deleteAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body("All students deleted");
    }
    @GetMapping("count")
    public ResponseEntity<?> getNumberOfStudents(){
        Long  numberOfStudents = studentService.getNumberOfStudents();
        return ResponseEntity.status(HttpStatus.OK).body("The total number of students is: %s".formatted(numberOfStudents));
    }
}
