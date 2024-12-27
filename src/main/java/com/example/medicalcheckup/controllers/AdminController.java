package com.example.medicalcheckup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.services.MCUServices;

@Controller
public class AdminController {
    @Autowired
    private MCUServices mcuServices;


    @GetMapping("/admin")
    public String landingPage() {
        return "admin/homeAdmin"; // Halaman landing
    }

    //ngambil semua paket
    @GetMapping("/admin/mcu")
    public ResponseEntity<List<MCU>> getAllMCUPackages() {
        List<MCU> packages = mcuServices.getAllMCUPackcages();
        return ResponseEntity.ok(packages);
    }

    //ngambil paket berdasarkan id
    @GetMapping("/admin/mcu/{id}")
    public ResponseEntity<MCU> getMCUPackageById(@PathVariable int id) {
        return mcuServices.getAllMCUPackcages().stream()
                .filter(packageItem -> packageItem.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //nambah paket
    @PostMapping("/admin/mcu/add")
    public String addMCUPackage( @RequestParam String nama, 
                                @RequestParam String kategori, 
                                @RequestParam String detail, 
                                @RequestParam int harga) {
        MCU mcu = new MCU();
        mcu.setNama(nama);
        mcu.setCategory(kategori);
        mcu.setDetail(detail);
        mcu.setHarga(harga);
        mcuServices.saveMCUPackage(mcu);
        return "redirect:/admin";
    }

    //update paket
    @PutMapping("/admin/mcu/update/{id}")
    public String updateMCUPackage(@PathVariable int id,
                                                    @RequestParam String nama, 
                                                    @RequestParam String kategori, 
                                                    @RequestParam String detail, 
                                                    @RequestParam int harga) {
        MCU mcu = new MCU();
        mcu.setNama(nama);
        mcu.setCategory(kategori);
        mcu.setDetail(detail);
        mcu.setHarga(harga);
        mcuServices.updateMCUPackage(id, mcu);
        return "redirect:/admin";
    }

    //delete paket
    @DeleteMapping("/admin/mcu/delete/{id}")
    public String deleteMCUPackage(@PathVariable int id) {
        mcuServices.deleteMCUPackage(id);
        return "redirect:/admin";
    }

    //nyari user
    @GetMapping("/admin/mcu/search")
    public ResponseEntity<List<String>> searchUser(@RequestParam String keyword) {
        List<String> users = mcuServices.searchUser(keyword);
        return ResponseEntity.ok(users);
    }

}