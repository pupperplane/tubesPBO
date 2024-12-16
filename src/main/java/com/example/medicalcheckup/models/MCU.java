package com.example.medicalcheckup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "MCU")
@Getter
@Setter
public class MCU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama", unique = true, nullable = false)
    private String nama;

    @Column(name = "Category", nullable = false)
    private String category;

    @Column(name = "detail", nullable = false, length = 255)
    private String detail;

}
