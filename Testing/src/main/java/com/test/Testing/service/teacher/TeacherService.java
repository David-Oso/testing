package com.test.Testing.service.teacher;

import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.model.Teacher;

public interface TeacherService {
    LoginResponse login(TeacherLoginRequest teacherLoginRequest);
    Teacher getTeacherByEmail(String email);
}
