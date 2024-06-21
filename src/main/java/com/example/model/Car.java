package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^(0[1-9]|[1-9][0-9])[A-C]-[0-9]{5}$|^(0[1-9]|[1-9][0-9])NG-[0-9]{5}$" , message = "Sai định dạng biển số , vui lòng nhập theo định dạng sau :XX[A,B,C,NG]-XXXXX")
    private String code;
    @NotEmpty
    private String name;
    @NotEmpty
    private String producer;
    @Min(200000000)
    private double price;

    @ManyToOne
    private Type type;
}
