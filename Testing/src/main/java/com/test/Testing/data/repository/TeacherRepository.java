package com.test.Testing.data.repository;

import com.test.Testing.data.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByAppUser_Email(String email);
}
