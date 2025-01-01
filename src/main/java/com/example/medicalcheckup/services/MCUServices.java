package com.example.medicalcheckup.services;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
            existingMCU.setHarga(updatedMCU.getHarga());
            repository.save(existingMCU);
        }
    }

    public void deleteMCUPackage(int id) {
        repository.deleteById(id);
    }

    public List<String> searchUser(String keyword) {
        return List.of("User1", "User2");
    }
    public List<String> formatMCUHarga(List<MCU> mcuList) {
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        
        return mcuList.stream()
                      .map(mcu -> formatRupiah.format(mcu.getHarga())) 
                      .collect(Collectors.toList()); 
    }
    
}
