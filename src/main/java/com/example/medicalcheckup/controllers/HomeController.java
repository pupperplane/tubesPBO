package com.example.medicalcheckup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.medicalcheckup.models.User;
import com.example.medicalcheckup.repositories.UserRepository;


@Controller
public class HomeController {
    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/")
    public String landingPage() {
        return "index"; // Halaman landing
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Halaman login
    }

    @GetMapping("/home")
    public String home(Model model) {
        // Ambil Authentication dari SecurityContext untuk mendapatkan username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Username yang digunakan untuk login
        
        // Ambil User dari database berdasarkan username
        User user = UserRepository.findByUsername(username);  // Cari user berdasarkan username
        
        // Jika user ditemukan, kirimkan nama ke view
        model.addAttribute("nama", user);  // Kirim nama ke view
        
        
        return "home";  // Kembali ke halaman home
    }
}
