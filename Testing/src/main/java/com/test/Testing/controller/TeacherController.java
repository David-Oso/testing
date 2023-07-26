package com.test.Testing.controller;

import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.model.Teacher;
import com.test.Testing.service.teacher.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/testing/teacher/")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("login")
    public ResponseEntity<?> teacherLogin(@Valid @RequestBody TeacherLoginRequest teacherLoginRequest){
        LoginResponse response = teacherService.login(teacherLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("get/email")
    public ResponseEntity<?> getTeacherByEmail(@Valid @RequestParam String email){
        Teacher teacher = teacherService.getTeacherByEmail(email);
        return ResponseEntity.ok(teacher);
    }
}
