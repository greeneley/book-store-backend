package com.htdinh.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER", schema = "bookstore")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ADDRESS_ORDER_ID", nullable = false)
    private AddressOrder addressOrder;

    @NotNull
    @Lob
    @Column(name = "ORDER_STATUS", nullable = false)
    private String orderStatus;

    @Size(max = 50)
    @NotNull
    @Column(name = "ORDER_NUMBER", nullable = false, length = 50)
    private String orderNumber;

    @Lob
    @Column(name = "PAYMENT")
    private String payment;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @Column(name = "CRT_ID")
    private Long crtId;

    @Column(name = "CRT_DT")
    private LocalDateTime crtDt;

    @Column(name = "UPDT_ID")
    private Long updtId;

    @Column(name = "UPDT_DT")
    private LocalDateTime updtDt;


    //TODO [JPA Buddy] generate columns from DB
}