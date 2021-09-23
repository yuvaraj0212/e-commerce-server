package com.example.demo.model;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_orderdetails")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false,referencedColumnName = "id")
	private Order order;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false,referencedColumnName = "id")
	private ProductModel productModel;
	private String quanity;
	private String price;
	private String amount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public ProductModel getProduct() {
		return productModel;
	}
	public void setProduct(ProductModel productModel) {
		this.productModel = productModel;
	}
	public String getQuanity() {
		return quanity;
	}
	public void setQuanity(String quanity) {
		this.quanity = quanity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public OrderDetail(Long id, Order order, ProductModel productModel, String quanity, String price, String amount) {
		super();
		this.id = id;
		this.order = order;
		this.productModel = productModel;
		this.quanity = quanity;
		this.price = price;
		this.amount = amount;
	}
	public OrderDetail() {
		super();
	}	
}
