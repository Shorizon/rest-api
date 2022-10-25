package com.qa.java.productSQL.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
	
	private int id;
	private String name;
	private double price;

}
