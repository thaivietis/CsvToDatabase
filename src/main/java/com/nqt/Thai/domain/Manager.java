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
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CustomValidate(column = "STT")
    private int id;
    @CustomValidate(column = "Name")
    private String name;
    @CustomValidate(column = "Address")
    private String address;
}
