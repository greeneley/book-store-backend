package com.htdinh.bookstore.model;

import com.htdinh.bookstore.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class Order {
    
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private Integer orderId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, referencedColumnName = "USER_ID")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Size(max = 50)
    @Column(name = "ORDER_DATE", length = 50)
    private String orderDate;

    @Size(max = 50)
    @Column(name = "PAYMENT_METHOD", length = 50)
    private String paymentMethod;

    @Column(name = "TOTAL")
    private Long total;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

}
