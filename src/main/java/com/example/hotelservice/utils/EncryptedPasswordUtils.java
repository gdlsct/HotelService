package com.example.hotelservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {

    // Encrypt Password with BCryptPasswordEncoder
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}