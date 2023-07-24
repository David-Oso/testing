package com.test.Testing.service.student;

import com.test.Testing.data.dto.request.LoginRequest;
import com.test.Testing.data.dto.request.RegisterStudentRequest;
import com.test.Testing.data.model.AppUser;
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
        student.setAppuser(appUser);
        student.setGender(registerStudentRequest.getGender());
        studentRepository.save(student);
        return "Student Registration Successful";
    }

    @Override
    public String login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public String writeToAStudent(Long firstStudentId, Long secondStudentId) {
        return null;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public void deleteAStudent(Long studentId) {

    }

    @Override
    public void deleteAllStudents() {

    }
}
