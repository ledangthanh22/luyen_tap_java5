package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "KhachHang")
public class Customer {
    @Id
    @Column(name = "MaKhachHang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotBlank(message = "Name cannot be left blank")
    @Column(name = "TenKhachHang")
    private String name;

    @NotBlank(message = "Phone number cannot be left blank")
    @Column(name = "SoDienThoai")
    private String phoneNumber;

    @NotNull(message = "Please select your gender!")
    @Column(name = "GioiTinh")
    private Boolean gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HangKhachHang",referencedColumnName = "MaHang")
    private CustomerClass customerClass;
}
