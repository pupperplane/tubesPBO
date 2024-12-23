package com.example.medicalcheckup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicalcheckup.models.Cart;
import com.example.medicalcheckup.models.Cart_Item;

public interface CartItemRepository  extends JpaRepository<Cart_Item, Integer>{
    List<Cart_Item> findByCartId(int cartId);
    boolean existsByCartAndMcuId(Cart cart, int mcuId);
    void deleteByCartAndMcuId(Cart cart, int mcuId);
    Cart_Item findById(int id);
}
