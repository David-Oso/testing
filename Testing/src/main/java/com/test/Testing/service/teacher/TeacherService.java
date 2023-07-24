package com.test.Testing.service.teacher;

import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.model.Teacher;

public interface TeacherService {
    String login(TeacherLoginRequest teacherLoginRequest);
    Teacher getTeacherByEmail(String email);
}
