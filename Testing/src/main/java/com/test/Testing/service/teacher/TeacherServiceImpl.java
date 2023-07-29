package com.test.Testing.service.teacher;

import com.test.Testing.config.TeacherConfig;
import com.test.Testing.data.dto.request.TeacherLoginRequest;
import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Teacher;
import com.test.Testing.data.repository.TeacherRepository;
import com.test.Testing.security.AuthenticatedUser;
import com.test.Testing.security.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    private final TeacherConfig teacherConfig;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

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
    public LoginResponse login(TeacherLoginRequest teacherLoginRequest) {
        LoginResponse loginResponse = null;
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(teacherLoginRequest.getEmail(), teacherLoginRequest.getPassword()));

            Map<String, Object> claims = authentication.getAuthorities().stream()
                    .collect(Collectors.toMap(authority -> "claim", Function.identity()));

            AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();
            String email = user.getUsername();
            Teacher teacher = getTeacherByEmail(email);

            JwtTokenResponse jwtResponse = this.generateTokens(claims, email);
            if (teacher.getIdentity().equals(teacherLoginRequest.getIdentity())){
                loginResponse = LoginResponse.builder()
                        .message("Authentication Successful")
                        .isSuccess(true)
                        .jwtTokenResponse(jwtResponse)
                        .build();
            }
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
        return loginResponse;
    }

    private JwtTokenResponse generateTokens(Map<String, Object> claims, String email) {
        String accessToken = jwtUtils.generateAccessToken(claims, email);
        String refreshToken = jwtUtils.generateRefreshToken(email);

        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.findByAppUser_Email(email).orElseThrow(
                ()-> new RuntimeException("Teacher not found"));
    }
}
