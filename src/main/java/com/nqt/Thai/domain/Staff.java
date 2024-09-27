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
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CustomValidate(column = "STT")
    private int staffId;
    @CustomValidate(column = "Name")
    private String staffName;
    @CustomValidate(column = "Email")
    private String staffEmail;
}
