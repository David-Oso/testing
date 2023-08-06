package com.test.Testing.service.student;

import com.test.Testing.config.security.jwtToken.TestingToken;
import com.test.Testing.config.security.jwtToken.TestingTokenService;
import com.test.Testing.config.security.services.JwtService;
import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.dto.response.RegisterResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Student;
import com.test.Testing.data.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TestingTokenService testingTokenService;
    @Override
    public RegisterResponse registerStudent(RegisterStudentRequest registerStudentRequest) {
        Student student = new Student();
        AppUser appUser = getAppUser(registerStudentRequest);

        student.setAppUser(appUser);
        student.setGender(registerStudentRequest.getGender());
        Student savedStudent = studentRepository.save(student);

        JwtTokenResponse jwtResponse = this.getJwtTokenResponse(savedStudent);
        return RegisterResponse.builder()
                .message("Registration Successful")
                .isSuccess(true)
                .jwtTokenResponse(jwtResponse)
                .build();
    }

    private JwtTokenResponse getJwtTokenResponse(Student student) {
        AppUser appUser = student.getAppUser();
        String email = appUser.getEmail();
        final String accessToken = jwtService.generateAccessToken(email);
        final String refreshToken = jwtService.generateRefreshToken(email);

        savedTestingToken(appUser, accessToken);
        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void savedTestingToken(AppUser appUser, String accessToken) {
        TestingToken testingToken = TestingToken.builder()
                .token(accessToken)
                .appUser(appUser)
                .isExpired(false)
                .isRevoked(false)
                .build();
        testingTokenService.saveToken(testingToken);
    }

    private AppUser getAppUser(RegisterStudentRequest registerStudentRequest) {
        AppUser appUser = modelMapper.map(registerStudentRequest, AppUser.class);
        String encodedPassword = passwordEncoder.encode(registerStudentRequest.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setRole(Role.STUDENT);
        return appUser;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String email = authentication.getPrincipal().toString();
        Student student = getStudentByEmail(email);
        JwtTokenResponse jwtTokenResponse = this.getJwtTokenResponse(student);
        return LoginResponse.builder()
                .jwtTokenResponse(jwtTokenResponse)
                .build();
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                ()-> new RuntimeException("Student with this is not found"));
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.getStudentsByAppUser_Email(email).orElseThrow(
                ()-> new RuntimeException("Student with this email not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student = getStudentById(studentId);
        AppUser appUser = student.getAppUser();
//        jwtTokenService.deleteAllTokensByUserId(appUser.getId());
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
//        jwtTokenService.deleteAllTokens();
        studentRepository.deleteAll();
    }

    @Override
    public Long getNumberOfStudents() {
        return studentRepository.count();
    }
}
