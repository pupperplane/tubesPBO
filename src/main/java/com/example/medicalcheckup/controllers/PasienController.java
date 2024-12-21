package com.example.medicalcheckup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.repositories.MCURepository;

@Controller
public class PasienController {
    @Autowired  
    private MCURepository mcuRepository;

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

}
