package com.qa.java.productSQL.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "product_details")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;
	
	@NotNull
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long !!")
	@Pattern(regexp = "^[A-Za-z0-9]*", message = "Invalid name - must only contain alphanumeric characters !!")
	@Column(name = "product_name")
	private String name;
	
	@NotNull
	@Min(0)
	@Column(name = "product_price")
	private double price;
	
}
