package com.example.medicalcheckup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medicalcheckup.models.Cart;
import com.example.medicalcheckup.models.Hospital;
import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.repositories.HospitalRepository;
import com.example.medicalcheckup.repositories.MCURepository;
import com.example.medicalcheckup.services.CartItemServices;
import com.example.medicalcheckup.services.CartServices;
import com.example.medicalcheckup.services.UserService;

import jakarta.transaction.Transactional;

@Controller
public class PasienController {
    @Autowired  
    private MCURepository mcuRepository;

    @Autowired
    private UserService userService; 

    @Autowired
    private CartServices cartService; 

    @Autowired
    private CartItemServices cartItemService; 

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("/home/{cName}")
    public String landingPage(@PathVariable String cName, Model model) {
        List<MCU> mcuList = mcuRepository.findByCategory(cName);

        if (mcuList.isEmpty()) {
            model.addAttribute("errorMessage", "Tidak ada data untuk kategori " + cName);
        } else {
            model.addAttribute("mcu", mcuList);
        }

        return "pasien/index"; 
    }

    @GetMapping("/home/cart")
    public String cart (@RequestParam(required=false) String region, Model model) {
        List<Hospital> hospitals = hospitalRepository.findByRegion(region);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userService.getUserIdByUsername(name);
        Cart cart = cartService.getKeranjangByUserid(id);
        int a = cart.getId();
        List<MCU> mcu = cartItemService.getMCUByCartId(a);
        model.addAttribute("mcu", mcu);
        model.addAttribute("region", region);
        model.addAttribute("hospitals", hospitals);
        return "pasien/test"; 
    }

    @PostMapping("/home/{id}")
    public String saveItemtoCart(@PathVariable int id, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUserIdByUsername(username);
        Cart cart = cartService.getKeranjangByUserid(userId);
        cartItemService.addItemToCart(cart, id);
        return "redirect:/home"; 
    }

    @DeleteMapping("/home/cart/{id}")
    @Transactional
    public String deleteItemfromCart(@PathVariable int id, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUserIdByUsername(username);
        Cart cart = cartService.getKeranjangByUserid(userId);
        cartItemService.removeItemFromCart(cart,id);
        return "redirect:/home/cart"; 
    }

}
