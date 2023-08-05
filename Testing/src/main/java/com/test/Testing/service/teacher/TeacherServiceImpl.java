package com.test.Testing.service.teacher;

import com.test.Testing.config.appConfig.TeacherConfig;
import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Teacher;
import com.test.Testing.data.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final TeacherConfig teacherConfig;
//    private final AuthenticationManager authenticationManager;
//    private final PasswordEncoder passwordEncoder;

//    @PostConstruct
    private void registerInAppTeacher(){
        Teacher teacher = new Teacher();
        AppUser appUser = new AppUser();
        appUser.setFirstName(teacherConfig.getFirstName());
        appUser.setLastName(teacherConfig.getLastName());
        appUser.setPhoneNumber(teacherConfig.getPhoneNumber());
        appUser.setEmail(teacherConfig.getEmail());

//        String encodedPassword = passwordEncoder.encode(teacherConfig.getPassword());
        String encodedPassword = teacherConfig.getPassword();
        appUser.setPassword(encodedPassword);
        appUser.setRole(Role.TEACHER);

        teacher.setAppUser(appUser);
        teacher.setIdentity(teacherConfig.getIdentity());
        teacherRepository.save(teacher);
    }

    @Override
    public LoginResponse login(TeacherLoginRequest teacherLoginRequest) {
        return new LoginResponse();
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByAppUser_Email(email).orElseThrow(
                ()-> new RuntimeException("Teacher not found"));
    }
}
