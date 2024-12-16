package com.example.medicalcheckup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.medicalcheckup.models.User;
import com.example.medicalcheckup.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(String username, String password, String name, String phone) {
        if (userRepository.findByUsername(username) != null) {
            return false; 
        }

        String encodedPassword = passwordEncoder.encode(password);


        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setNama(name);
        user.setNama(phone);
        user.setAuthorities("USER"); 

        userRepository.save(user);
        return true;
    }
}
