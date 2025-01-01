package com.example.medicalcheckup.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="hospital")
@Getter
@Setter
public class Hospital extends BaseEntity {

    private String name;

    private String region;

    private String address;

    private String contact;
}
