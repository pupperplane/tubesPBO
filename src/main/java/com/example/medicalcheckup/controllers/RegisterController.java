package com.example.medicalcheckup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medicalcheckup.models.Cart;
import com.example.medicalcheckup.models.User;
import com.example.medicalcheckup.services.CartServices;
import com.example.medicalcheckup.services.UserService;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartServices cartService;

    @GetMapping("/req/register")
    public String signup() {
        return "signup"; // Halaman signup
    }

    @PostMapping("/req/register")
    public String registerUser(@RequestParam String username, 
                               @RequestParam String password, 
                               @RequestParam String name, 
                               @RequestParam String phone, 
                               Model model) {
        boolean userCreated = userService.registerUser(username, password, name, phone);
        User userCreateds = userService.getUserById(username);
        if (userCreated) {
            Cart cart = new Cart();
            cart.setUser(userCreateds); 
            cart.setStatus(Cart.Status.PENDING);
            cartService.saveKeranjang(cart);
            model.addAttribute("message", "Registrasi berhasil, silakan login!");
            return "login"; // Redirect ke halaman login setelah registrasi berhasil
        } else {
            model.addAttribute("error", "Username sudah terdaftar");
            return "signup"; // Jika username sudah ada, tetap di halaman registrasi
        }
    }
}
