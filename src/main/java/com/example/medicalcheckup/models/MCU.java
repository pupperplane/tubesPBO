package com.example.medicalcheckup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "MCU")
@Getter
@Setter
public class MCU extends BaseEntity{

    @Column(name = "nama", unique = true, nullable = false)
    private String nama;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "detail", nullable = false, length = 255)
    private String detail;

    @Column(name = "harga", nullable = false, length = 255)
    private float harga;

}
