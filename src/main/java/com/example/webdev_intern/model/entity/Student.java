package com.example.webdev_intern.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String sbd;

    private Double toan;
    private Double nguVan;
    private Double ngoaiNgu;
    private Double vatLi;
    private Double hoaHoc;
    private Double sinhHoc;
    private Double lichSu;
    private Double diaLi;
    private Double gdcd;

    private String maNgoaiNgu;
}
