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

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PhieuGiamGia")
public class Coupons {

    @Id
    @Column(name = "MaPhieu")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private String code;

    @NotBlank(message = "Code cannot be blank!")
    @Column(name = "TenPhieu")
    private String name;

    @Column(name = "NgayBatDau")
    private Date startDay;

    @Column(name = "NgayKetThuc")
    private Date endDay;

    @NotNull(message = "Reduce Value cannot be blank!")
    @Column(name = "GiaTriGiam")
    private Double reduceValue;

    @NotNull(message = "Maximum Reduce Value cannot be blank!")
    @Column(name = "GiaTriGiamToiDa")
    private Double maximumReduceValue;

    @Column(name = "TrangThai")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NguoiSoHuu",referencedColumnName = "MaKhachHang")
    private Customer  customer;

}
