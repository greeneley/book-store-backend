package com.htdinh.bookstore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", nullable = false)
    private Integer addressId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "USER_ID")
    private User user;

    @Size(max = 250)
    @NotNull
    @Column(name = "PROVINCE", nullable = false, length = 250)
    private String province;

    @Size(max = 250)
    @NotNull
    @Column(name = "DISTRICT", nullable = false, length = 250)
    private String district;

    @Size(max = 250)
    @NotNull
    @Column(name = "WARD", nullable = false, length = 250)
    private String ward;

    @Size(max = 250)
    @Column(name = "ORDER_RECEIVER_ADDRESS", length = 250)
    private String orderReceiverAddress;

    @Size(max = 250)
    @Column(name = "RECEIVER_NAME", length = 250)
    private String receiverName;

    @Size(max = 250)
    @Column(name = "RECEIVER_PHONE", length = 250)
    private String receiverPhone;

    @Size(max = 50)
    @Column(name = "CREATED_AT", length = 50)
    private String createdAt;

    @OneToMany(mappedBy = "address")
    private Set<Order> orders = new LinkedHashSet<>();

}