package com.example.medicalcheckup.controllers;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.medicalcheckup.models.Cart;
import com.example.medicalcheckup.models.Cart.Status;
import com.example.medicalcheckup.models.Hospital;
import com.example.medicalcheckup.models.MCU;
import com.example.medicalcheckup.repositories.HospitalRepository;
import com.example.medicalcheckup.repositories.MCURepository;
import com.example.medicalcheckup.services.CartItemServices;
import com.example.medicalcheckup.services.CartServices;
import com.example.medicalcheckup.services.MCUServices;
import com.example.medicalcheckup.services.UserService;

import jakarta.transaction.Transactional;

@Controller
public class PasienController {
    @Autowired  
    private MCURepository mcuRepository;

    @Autowired
    private UserService userService; 

    @Autowired
    private MCUServices mcuService; 

    @Autowired
    private CartServices cartService; 

    @Autowired
    private CartItemServices cartItemService; 

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("/home/{cName}")
    public String landingPage(@PathVariable String cName, Model model) {
        List<MCU> mcuList = mcuRepository.findByCategory(cName);

        if (mcuList.isEmpty()) {
            model.addAttribute("errorMessage", "Tidak ada data untuk kategori " + cName);
        } else {
            List<String> formattedHargaList = mcuService.formatMCUHarga(mcuList);
            model.addAttribute("formattedHargaList", formattedHargaList);
            model.addAttribute("mcu", mcuList);
        }

        return "pasien/mcu"; 
    }

    @GetMapping("/home/cart")
    public String cart (@RequestParam(required=false) String region, Model model) {
        List<Hospital> hospitals = hospitalRepository.findByRegion(region);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userService.getUserIdByUsername(name);
        Cart cart = cartService.getKeranjangByUserid(id);
        if(cart == null){
            cart = new Cart();
            cart.setUser(userService.getUserById(name));
            cart.setStatus(Status.PENDING);
            cartService.saveKeranjang(cart);
        }
        int a = cart.getId();
        double total = cartItemService.getTotalHargaByCartId(a);
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String totalHarga = formatRupiah.format(total);
        List<MCU> mcu = cartItemService.getMCUByCartId(a);
        model.addAttribute("mcu", mcu);
        model.addAttribute("cartId", cart.getId());
        model.addAttribute("region", region);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("totalHarga", totalHarga);
        return "pasien/test"; 
    }

    @PostMapping("/home/{id}")
    public String saveItemtoCart(@PathVariable int id, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUserIdByUsername(username);
        Cart cart = cartService.getKeranjangByUserid(userId);
        cartItemService.addItemToCart(cart, id);
        return "redirect:/home"; 
    }

    @DeleteMapping("/home/cart/{id}")
    @Transactional
    public String deleteItemfromCart(@PathVariable int id, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUserIdByUsername(username);
        Cart cart = cartService.getKeranjangByUserid(userId);
        cartItemService.removeItemFromCart(cart,id);
        return "redirect:/home/cart"; 
    }
    

    @PostMapping("/home/cart/{id}")
    public String checkout(@PathVariable int id,
                            @RequestParam String region, 
                            @RequestParam String hospital,
                            @RequestParam LocalDate date) {
        Cart cart = cartService.getKeranjangById(id);
        cart.setDaerah(region);
        cart.setRumah_sakit(hospital);
        cart.setTanggal_periksa(date);
        cart.setTotal_harga(cartItemService.getTotalHargaByCartId(id));
        LocalDate currentDate = LocalDate.now();   
        cart.setTanggal_CO(currentDate);
        cart.setStatus(Status.COMPLETED);
        cartService.saveKeranjang(cart);
        
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        cart = new Cart();
        cart.setUser(userService.getUserById(name));
        cart.setStatus(Status.PENDING);
        cartService.saveKeranjang(cart);
   
        return "redirect:/home"; 
    }

    @GetMapping("/home/history")
    public String viewHistory(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userService.getUserIdByUsername(name);
        List<Cart> cartHistory = cartService.getKeranjangByUseridlist(id);
        model.addAttribute("cartHistory", cartHistory);
        return "pasien/history";
    }

    @GetMapping("/home/cart/{idC}")
    public String viewDetail(Model model, @PathVariable int idC) {
        Cart cart = cartService.getKeranjangById(idC);
        List <MCU> mcu =  cartItemService.getMCUByCartId(idC);
        model.addAttribute("mcu", mcu);
        model.addAttribute("cart", cart);
        return "pasien/historyDetail";
    }

}
