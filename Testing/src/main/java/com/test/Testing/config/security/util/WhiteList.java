package com.test.Testing.config.security.util;

import lombok.Getter;

@Getter
public class WhiteList {
    public static  String[] freeAccess(){
        return new String[]{
                "/api/v1/testing/student/register",
                "/api/v1/testing/student/login",
                "/api/v1/testing/teacher/login"
        };
    }

    public static String[] swagger() {
        return new String[]{
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "webjars/**",
                "/swagger-ui.html"
        };
    }
}
