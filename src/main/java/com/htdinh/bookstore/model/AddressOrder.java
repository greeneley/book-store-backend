package com.htdinh.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ADDRESS_ORDER", schema = "bookstore")
public class AddressOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ORDER_ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Size(max = 50)
    @Column(name = "PHONE", length = 50)
    private String phone;

    @Size(max = 50)
    @Column(name = "PROVINCE", length = 50)
    private String province;

    @Size(max = 50)
    @Column(name = "DISTRICT", length = 50)
    private String district;

    @Size(max = 50)
    @Column(name = "WARD", length = 50)
    private String ward;

    @Column(name = "CRT_ID", precision = 22)
    private Long crtId;

    @Size(max = 128)
    @Column(name = "CRT_DT", length = 128)
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID", precision = 22)
    private Long updtId;

    @Size(max = 128)
    @Column(name = "UPDT_DT", length = 128)
    private LocalDateTime updtDt;

    //TODO [JPA Buddy] generate columns from DB
}