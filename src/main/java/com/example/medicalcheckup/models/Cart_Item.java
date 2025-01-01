package com.example.medicalcheckup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class Cart_Item extends BaseEntity {
   
    @JsonIgnoreProperties("Cart_Item")
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;  

    @ManyToOne
    @JoinColumn(name = "mcu_id", nullable = false)
    private MCU mcu;  

}
