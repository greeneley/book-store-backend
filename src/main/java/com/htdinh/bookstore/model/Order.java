package com.htdinh.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", customer=" + customer + ", book=" + book + "]";
	}

}
