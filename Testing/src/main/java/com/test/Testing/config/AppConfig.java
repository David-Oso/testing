package com.test.Testing.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${teacher.firstname}")
    private String teacherFirstName;
    @Value("${teacher.lastname}")
    private String teacherLastName;
    @Value("${teacher.phone.number}")
    private String teacherPhoneNumber;
    @Value("${teacher.email}")
    private String teacherEmail;
    @Value("${teacher.password}")
    private String teacherPassword;
    @Value("${teacher.identity}")
    private String teacherIdentity;


    @Bean
    public TeacherConfig teacherConfig(){
        return new TeacherConfig(
                teacherFirstName,
                teacherLastName,
                teacherPhoneNumber,
                teacherEmail,
                teacherPassword,
                teacherIdentity
        );
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
