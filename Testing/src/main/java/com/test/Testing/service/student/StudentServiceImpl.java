package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.model.Role;
import com.test.Testing.data.model.Student;
import com.test.Testing.data.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Override
    public String registerStudent(RegisterStudentRequest registerStudentRequest) {
        Student student = new Student();
        AppUser appUser = new AppUser();
        appUser.setFirstName(registerStudentRequest.getFirstName());
        appUser.setLastName(registerStudentRequest.getLastName());
        appUser.setPhoneNumber(registerStudentRequest.getPhoneNumber());
        appUser.setEmail(registerStudentRequest.getEmail());
        appUser.setPassword(registerStudentRequest.getPassword());
        appUser.setRole(Role.STUDENT);

        student.setAppUser(appUser);
        student.setGender(registerStudentRequest.getGender());
        studentRepository.save(student);
        return "Student Registration Successful";
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Student student = getStudentByEmail(loginRequest.getEmail());
        AppUser appUser = student.getAppUser();
        if(appUser.getPassword().equals(loginRequest.getPassword()))
            return "Authentication Successful";
        else throw new RuntimeException("Incorrect Password");
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
    public void deleteAStudent(Long studentId) {
        studentRepository.deleteById(studentId);

    }

    @Override
    public void deleteAllStudents() {

    }

    @Override
    public Long studentCount() {
        return studentRepository.count();
    }
}
