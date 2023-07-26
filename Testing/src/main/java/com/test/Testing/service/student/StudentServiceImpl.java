package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.dto.response.RegisterResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Student;
import com.test.Testing.data.repository.StudentRepository;
import com.test.Testing.security.AuthenticatedUser;
import com.test.Testing.service.jwt.JwtTokenService;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final ModelMapper modelMapper;
    @Override
    public RegisterResponse registerStudent(RegisterStudentRequest registerStudentRequest) {
        Student student = new Student();
        AppUser appUser = getAppUser(registerStudentRequest);

        student.setAppUser(appUser);
        student.setGender(registerStudentRequest.getGender());
        Student savedStudent = studentRepository.save(student);

        String email = savedStudent
                .getAppUser().getEmail();

        JwtTokenResponse jwtResponse = jwtTokenService.getJwtTokens(email);
        return RegisterResponse.builder()
                .message("Registration Successful")
                .isSuccess(true)
                .jwtTokenResponse(jwtResponse)
                .build();
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
        try{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();
        String email = user.getUsername();

        JwtTokenResponse jwtResponse = jwtTokenService.getJwtTokens(email);
        return LoginResponse.builder()
                .message("Authentication Successful")
                .isSuccess(true)
                .jwtTokenResponse(jwtResponse)
                .build();
        }catch (Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
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
        studentRepository.deleteById(studentId);

    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

    @Override
    public Long getNumberOfStudents() {
        return studentRepository.count();
    }
}
