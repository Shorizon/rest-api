package com.qa.java.productSQL.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.java.productSQL.entity.Product;
import com.qa.java.productSQL.exception.ProductAlreadyExistsException;
import com.qa.java.productSQL.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock //creates the mock object
	private ProductRepository productRepository;
	
	@Autowired
	@InjectMocks
	private ProductServiceImpl productService;
	
	Product p1;
	Product p2;
	Product p3;
	
	List<Product> productList;
	
	@BeforeEach
	public void setUp() {
		
		p1 = new Product(1, "Phone", 249.99);
		p2 = new Product(2, "tvtest", 599.99);
		p3 = new Product(3, "Wallettest", 12.99);
		productList = Arrays.asList(p1, p2, p3);
	}
	
	@AfterEach
	public void tearDown() {
		p1 = null; p2 = null; p3 = null;
		productList = null;
	}
	
	@Test
	@DisplayName("save-product-test")
	public void given_Product_To_Save_Return_Saved_Product() throws ProductAlreadyExistsException{
		when(productRepository.findByName(any())).thenReturn(null);
		when(productRepository.save(any())).thenReturn(p1);
		Product savedProd = productService.addProduct(p1);
		assertNotNull(savedProd);
		assertEquals(1,savedProd.getId());
		verify(productRepository,times(1)).findByName(any());
		verify(productRepository,times(1)).save(any());
		
		
	}
	
	@Test
	@DisplayName("save-product-throws-exception-test")
	
	public void given_Existing_Product_To_Save_Method_Should_Throw_Exception() throws ProductAlreadyExistsException {
		when(productRepository.findByName(any())).thenReturn(p1);
	
		assertThrows(ProductAlreadyExistsException.class,()-> productService.addProduct(p1));
	}

	

}
