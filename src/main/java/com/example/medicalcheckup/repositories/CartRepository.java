package com.example.medicalcheckup.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalcheckup.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserId(int userId);
    Cart findByUserIdAndStatus(int userId, Cart.Status status);
    Cart findById(int id);
}
