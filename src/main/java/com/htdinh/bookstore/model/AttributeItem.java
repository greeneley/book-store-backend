package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ATTRIBUTE_ITEM", schema = "bookstore")
public class AttributeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTRIBUTE_ITEM_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID")
    private Attribute attribute;

    @Size(max = 50)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Size(max = 500)
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "CRT_ID", precision = 22)
    private Long crtId;

    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;
}