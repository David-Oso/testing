package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.dto.response.LoginResponse;
import com.test.Testing.data.dto.response.RegisterResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Student;
import com.test.Testing.data.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public RegisterResponse registerStudent(RegisterStudentRequest registerStudentRequest) {
        Student student = new Student();
        AppUser appUser = getAppUser(registerStudentRequest);

        student.setAppUser(appUser);
        student.setGender(registerStudentRequest.getGender());
        Student savedStudent = studentRepository.save(student);

        String email = savedStudent.getAppUser().getEmail();
//        JwtTokenResponse jwtResponse = this.generateTokens(new HashMap<>(), email);
        return RegisterResponse.builder()
                .message("Registration Successful")
                .isSuccess(true)
//                .jwtTokenResponse(jwtResponse)
                .build();
    }

    private AppUser getAppUser(RegisterStudentRequest registerStudentRequest) {
        AppUser appUser = modelMapper.map(registerStudentRequest, AppUser.class);
//        String encodedPassword = passwordEncoder.encode(registerStudentRequest.getPassword());
        String encodedPassword = registerStudentRequest.getPassword();
        appUser.setPassword(encodedPassword);
        appUser.setRole(Role.STUDENT);
        return appUser;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return new LoginResponse();
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
