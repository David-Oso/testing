package com.test.Testing.data.repository;

import com.test.Testing.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> getStudentsByAppUser_Email(String email);
}
