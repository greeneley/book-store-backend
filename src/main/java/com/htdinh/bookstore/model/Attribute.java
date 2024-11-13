package com.htdinh.bookstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ATTRIBUTE", schema = "bookstore")
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTRIBUTE_ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "CRT_ID")
    private Long crtId;

    @Column(name = "CRT_DT")
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID")
    private Long updtId;

    @Column(name = "UPDT_DT")
    private LocalDateTime updtDt;

}