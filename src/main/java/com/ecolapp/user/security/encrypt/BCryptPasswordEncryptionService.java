package com.ecolapp.user.security.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncryptionService implements PasswordEncryptionService {

    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptPasswordEncryptionService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode(password);
        return encryptedPassword;
    }

    @Override
    public boolean isPasswordMatch(String password, String encryptedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, encryptedPassword);
    }
}
