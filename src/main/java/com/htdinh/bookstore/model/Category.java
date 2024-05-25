package com.htdinh.bookstore.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID", nullable = false)
    private Integer categoryId;

    @Size(max = 150)
    @NotNull
    @Column(name = "CATEGORY_NAME", nullable = false, length = 150)
    private String categoryName;

    //TODO [JPA Buddy] generate columns from DB
}