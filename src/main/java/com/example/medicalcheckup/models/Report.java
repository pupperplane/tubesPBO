package com.example.medicalcheckup.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable=false)
    private Cart cart;

    @Column(name = "Tanggal_CO", nullable = true)
    private Date Tanggal_CO;

    @Column(name = "Total_Bayar", nullable = true)
    private double Total_Bayar;
}
