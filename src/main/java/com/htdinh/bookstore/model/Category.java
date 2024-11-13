package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY", schema = "bookstore")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAT_ID", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 128)
    private String name;

    @Column(name = "CRT_ID", precision = 22)
    private Long crtId;

    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;

    @OneToMany(mappedBy = "cat")
    private Set<ProductCategory> productCategories = new LinkedHashSet<>();

    // @Column(name = "IS_ACTIVE", nullable = false)
    // private Boolean isActive = true;

    // @ManyToOne
    // @JoinColumn(name = "PARENT_ID")
    // private Category parent;

    // @Column(name = "LEVEL", nullable = false)
    // private Integer level;

}