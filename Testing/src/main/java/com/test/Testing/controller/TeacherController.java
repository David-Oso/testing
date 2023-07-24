package com.test.Testing.controller;

import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.service.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/testing/teacher/")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("login")
    public ResponseEntity<?> teacherLogin(TeacherLoginRequest teacherLoginRequest){
        String response = teacherService.login(teacherLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
