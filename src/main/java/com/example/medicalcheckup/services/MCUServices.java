package com.example.medicalcheckup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.repositories.MCURepository;

@Service
public class MCUServices {
    @Autowired
    private MCURepository repository;

    public List<MCU> getAllMCUPackcages() {
        return repository.findAll();
    }

    public void saveMCUPackage(MCU mcu) {
        repository.save(mcu);
    }

    public void updateMCUPackage(int id, MCU updatedMCU) {
        MCU existingMCU = repository.findById(id).orElse(null);
        if (existingMCU != null) {
            existingMCU.setNama(updatedMCU.getNama());
            existingMCU.setCategory(updatedMCU.getCategory());
            existingMCU.setDetail(updatedMCU.getDetail());
            repository.save(existingMCU);
        }
    }

    public void deleteMCUPackage(int id) {
        repository.deleteById(id);
    }

    public List<String> searchUser(String keyword) {
        return List.of("User1", "User2");
    }
}
