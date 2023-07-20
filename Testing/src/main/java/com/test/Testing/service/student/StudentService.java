package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.RegisterStudentRequest;

public interface StudentService {
    String registerStudent(RegisterStudentRequest registerStudentRequest);
    String login();

}
