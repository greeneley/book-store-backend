package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT", schema = "bookstore")
public class Product {
    @Id
    @Column(name = "PRODUCT_ID", nullable = false, precision = 22)
    private BigDecimal id;

    @Size(max = 50)
    @Column(name = "NAME", length = 50)
    private String name;

}