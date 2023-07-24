package com.test.Testing.service.teacher;

import com.test.Testing.data.dto.request.TeacherLoginRequest;

public interface TeacherService {
    String login(TeacherLoginRequest teacherLoginRequest);
}
