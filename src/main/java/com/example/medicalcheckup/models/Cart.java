package com.example.medicalcheckup.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "tanggal_periksa", nullable = true)
    private LocalDate tanggal_periksa;

    @Column(name = "tanggal_CO", nullable = true)
    private LocalDate tanggal_CO;

    @JsonIgnoreProperties("cart")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @JsonIgnoreProperties("cart")
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<Cart_Item> Cart_Item;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    
    public enum Status {
        PENDING,
        COMPLETED,
    }

    @Column(name = "daerah", nullable = true)
    private String daerah;

    @Column(name = "rumah_sakit", nullable = true)
    private String rumah_sakit;

    @Column(name = "total_harga", nullable = false)
    private Double total_harga = 0.0;
}
