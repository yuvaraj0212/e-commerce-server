package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_orders")
public class Order implements Serializable {

	// private static final long serialVersionUID = -2576670215015463100L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date orderDate;

	private String customerName;

	private String customerAddress;

	private String customerEmail;

	private String customerPhone;

	private String customerState;

	private String customerZip;

	private double amount;
	private String orderNo;

	

}
