package com.test.Testing.service.teacher;

import com.test.Testing.config.TeacherConfig;
import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Teacher;
import com.test.Testing.data.repository.TeacherRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final TeacherConfig teacherConfig;

//    @PostConstruct
    private void registerInAppTeacher(){
        Teacher teacher = new Teacher();
        AppUser appUser = new AppUser();
        appUser.setFirstName(teacherConfig.getFirstName());
        appUser.setLastName(teacherConfig.getLastName());
        appUser.setPhoneNumber(teacherConfig.getPhoneNumber());
        appUser.setEmail(teacherConfig.getEmail());
        appUser.setPassword(teacherConfig.getPassword());
        appUser.setRole(Role.TEACHER);

        teacher.setAppUser(appUser);
        teacher.setIdentity(teacherConfig.getIdentity());
        teacherRepository.save(teacher);
    }

    @Override
    public String login(TeacherLoginRequest teacherLoginRequest) {
        Teacher teacher = getTeacherByEmail(teacherLoginRequest.getEmail());
        AppUser appUser = teacher.getAppUser();
        if(!(teacher.getIdentity().equals(teacherLoginRequest.getIdentity())))
            throw new RuntimeException("Invalid teacher identity");
        else if(!(appUser.getPassword().equals(teacherLoginRequest.getPassword())))
            throw new RuntimeException("Incorrect Password");
        else return "Authentication Successful";
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByAppUser_Email(email).orElseThrow(
                ()-> new RuntimeException("Teacher not found"));
    }
}
