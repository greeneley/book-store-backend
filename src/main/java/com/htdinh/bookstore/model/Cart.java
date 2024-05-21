package com.htdinh.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID", nullable = false)
    private Integer cartId;
    
    @NotNull
    @OneToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @Size(max = 50)
    @NotNull
    @Column(name = "CREATED_AT", nullable = false, length = 50)
    private String createdAt;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
