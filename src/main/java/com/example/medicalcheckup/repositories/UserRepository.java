package com.example.medicalcheckup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalcheckup.models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByNama(String nama);
    List<User> findAllByAuthorities(String authorities);
}
