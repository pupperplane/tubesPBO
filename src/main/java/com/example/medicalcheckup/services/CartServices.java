package com.example.medicalcheckup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalcheckup.models.Cart;
import com.example.medicalcheckup.models.Cart.Status;
import com.example.medicalcheckup.repositories.CartRepository;

@Service
public class CartServices {

    @Autowired
    private CartRepository keranjangRepository;

    // Simpan keranjang baru
    public Cart saveKeranjang(Cart keranjang) {
        return keranjangRepository.save(keranjang);
    }

    // Ambil semua keranjang
    public List<Cart> getAllKeranjang() {
        return keranjangRepository.findAllByStatus(Status.COMPLETED);
    }

    // Ambil keranjang berdasarkan ID
    public Cart getKeranjangById(int id) {
        return keranjangRepository.findById(id);
    }

    public Cart getKeranjangByUserid(int id) {
        Cart cart = keranjangRepository.findByUserIdAndStatus(id, Status.PENDING);
        return cart;
    }

    public List<Cart> getKeranjangByUseridlist(int id){
        List <Cart> cart = keranjangRepository.findAllByUserIdAndStatus(id, Status.COMPLETED);
        return cart;
    }

    public void deleteKeranjang(int id) {
        keranjangRepository.deleteById(id);
    }

    

    
}

