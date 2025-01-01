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
        return "index"; 
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); 
    
        User user = UserRepository.findByUsername(username);  
        
        model.addAttribute("nama", user);  
        
        return "pasien/home";  
    }

    @GetMapping("/access-denied")
    public String accesDenied(Model model) {
        
        model.addAttribute("error", "Maaf anda Bukan admin"); 
        
        return "exception/accesDenied";  
    }
}
