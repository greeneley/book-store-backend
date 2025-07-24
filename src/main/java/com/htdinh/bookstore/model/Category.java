package com.htdinh.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Size(max = 128)
    @NotNull
    @Column(name = "SLUG", nullable = false, length = 128)
    private String slug;

    @Column(name = "PARENT_CAT_ID")
    private Long parentCatId;
}