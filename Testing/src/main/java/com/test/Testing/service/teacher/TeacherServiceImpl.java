package com.test.Testing.service.teacher;

import com.test.Testing.config.appConfig.TeacherConfig;
import com.test.Testing.config.security.jwtToken.TestingToken;
import com.test.Testing.config.security.jwtToken.TestingTokenService;
import com.test.Testing.config.security.services.JwtService;
import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Teacher;
import com.test.Testing.data.repository.TeacherRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final TeacherConfig teacherConfig;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TestingTokenService testingTokenService;
    
    @PostConstruct
    private void registerInAppTeacher(){
        Teacher teacher = new Teacher();
        AppUser appUser = new AppUser();
        appUser.setFirstName(teacherConfig.getFirstName());
        appUser.setLastName(teacherConfig.getLastName());
        appUser.setPhoneNumber(teacherConfig.getPhoneNumber());
        appUser.setEmail(teacherConfig.getEmail());

        String encodedPassword = passwordEncoder.encode(teacherConfig.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setRole(Role.TEACHER);

        teacher.setAppUser(appUser);
        teacher.setIdentity(teacherConfig.getIdentity());
        teacherRepository.save(teacher);
    }

    @Override
    public LoginResponse login(TeacherLoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));
        
        String email = authentication.getPrincipal().toString();
        Teacher teacher = this.getTeacherByEmail(email);
        if(loginRequest.getIdentity().equals(teacher.getIdentity())){
            JwtTokenResponse jwtResponse = this.getJwtTokenResponse(teacher);
            return LoginResponse.builder()
                    .jwtTokenResponse(jwtResponse)
                    .build();
        }
        else throw new RuntimeException("Identity is incorrect");
    }

    private JwtTokenResponse getJwtTokenResponse(Teacher teacher) {
        AppUser appUser = teacher.getAppUser();
        String email = appUser.getEmail();
        String accessToken = jwtService.generateAccessToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);

        saveTestingToken(appUser, accessToken);
        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveTestingToken(AppUser appUser, String accessToken) {
        TestingToken testingToken = TestingToken.builder()
                .token(accessToken)
                .appUser(appUser)
                .isRevoked(false)
                .isExpired(false)
                .build();
        testingTokenService.saveToken(testingToken);
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByAppUser_Email(email).orElseThrow(
                ()-> new RuntimeException("Teacher not found"));
    }
}
