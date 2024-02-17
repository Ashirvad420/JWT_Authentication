package com.NewProject.NewPage;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestClass {

    public static void main(String[] args) {

        PasswordEncoder  passwordEncoder =  new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("Testing"));
    }
}

// BCryptPasswordEncoder encode any password and this is built in feature in spring security