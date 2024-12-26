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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.services.MCUServices;

@Controller
public class AdminController {
    @Autowired
    private MCUServices mcuServices;

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
    public ResponseEntity<String> addMCUPackage(@RequestBody MCU mcu) {
        mcuServices.saveMCUPackage(mcu);
        return ResponseEntity.ok("MCU package added successfully.");
    }

    //update paket
    @PutMapping("/admin/mcu/update/{id}")
    public ResponseEntity<String> updateMCUPackage(@PathVariable int id, @RequestBody MCU updatedMCU) {
        mcuServices.updateMCUPackage(id, updatedMCU);
        return ResponseEntity.ok("MCU package updated successfully.");
    }

    //delete paket
    @DeleteMapping("/admin/mcu/delete/{id}")
    public ResponseEntity<String> deleteMCUPackage(@PathVariable int id) {
        mcuServices.deleteMCUPackage(id);
        return ResponseEntity.ok("MCU package deleted successfully.");
    }

    //nyari user
    @GetMapping("/admin/mcu/search")
    public ResponseEntity<List<String>> searchUser(@RequestParam String keyword) {
        List<String> users = mcuServices.searchUser(keyword);
        return ResponseEntity.ok(users);
    }

}
