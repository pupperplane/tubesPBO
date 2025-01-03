package com.example.medicalcheckup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalcheckup.models.MCU;

@Repository
public interface MCURepository extends JpaRepository<MCU, Integer>  {
    MCU findByNama(String nama);
    List<MCU> findByCategory(String category);
}
