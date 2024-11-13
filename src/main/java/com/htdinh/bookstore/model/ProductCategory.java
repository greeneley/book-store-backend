package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_CATEGORY", schema = "bookstore")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CAT_ID", nullable = false)
    private Category cat;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;


    @Column(name = "CRT_ID")
    private Long crtId;

    @Column(name = "CRT_DT")
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID")
    private Long updtId;

    @Column(name = "UPDT_DT")
    private LocalDateTime updtDt;

}