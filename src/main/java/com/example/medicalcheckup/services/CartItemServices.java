package com.example.medicalcheckup.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalcheckup.models.Cart;
import com.example.medicalcheckup.models.Cart_Item;
import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.repositories.CartItemRepository;
import com.example.medicalcheckup.repositories.MCURepository;


@Service
public class CartItemServices {

    @Autowired
    private CartItemRepository itemKeranjangRepository;

    @Autowired
    private MCURepository mcuRepository;
    

    // Menambah item ke dalam keranjang
    public Cart_Item addItemToCart(Cart cart, int id) {
        Cart_Item cartItem = new Cart_Item();
        MCU mcu = mcuRepository.findById(id).orElse(null);
        if(!itemKeranjangRepository.existsByCartAndMcuId(cart, id)){
            cartItem.setCart(cart);
            cartItem.setMcu(mcu);
            return itemKeranjangRepository.save(cartItem);
        }else{
            return null;
        }
    }

    // Menghapus item dari keranjang
    public void removeItemFromCart(Cart cart, int id) {
        itemKeranjangRepository.deleteByCartAndMcuId(cart, id);
    }

    // Mendapatkan semua item dalam keranjang tertentu
    public List<MCU> getMCUByCartId(int cartId) {
        List<Cart_Item> cartItems = itemKeranjangRepository.findByCartId(cartId);
        return cartItems.stream()
                        .map(Cart_Item::getMcu)
                        .collect(Collectors.toList());
    }
}
