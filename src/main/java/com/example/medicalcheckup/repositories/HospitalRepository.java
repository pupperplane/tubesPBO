package com.example.medicalcheckup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalcheckup.models.Hospital;

@Repository
public interface  HospitalRepository extends JpaRepository<Hospital, Integer>{
    List<Hospital> findByRegion(String region);
}
