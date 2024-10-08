package com.nqt.Thai.domain;

import com.nqt.Thai.validate.CustomValidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CustomValidate(column = "STT")
    private int id;
    @CustomValidate(column = "Full Name")
    private String fullName;
    @CustomValidate(column = "Age")
    private int age;
}
