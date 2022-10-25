package com.qa.java.productSQL.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.java.productSQL.entity.Product;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProductRepositoryTest {
	
	@Autowired
	ProductRepository productRepository;
	
	
	Product p1;
	Product p2;
	Product p3;
	
	List<Product> productList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE
		

		p1 = new Product(1, "Phone", 249.99);
		p2 = new Product(2, "tvtest", 599.99);
		p3 = new Product(3, "Wallettest", 12.99);
		productList = Arrays.asList(p1, p2, p3);

		
	}
	
	@AfterEach
	public void tearDown() {
		p1 = null; p2 = null; p3 = null;
		productRepository.deleteAll();
		productList = null;
	}
	
	
	@Test
	@DisplayName("save-product-test")
	public void given_Product_To_Save_Return_Saved_Product() {
		Product savedProduct = productRepository.save(p1);
		assertNotNull(savedProduct);
		assertEquals("Phone", savedProduct.getName());
	}
	
	
	@Test
	@DisplayName("get-product-list-test")
	public void given_AllProducts_Return_Product_List() {
		
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
		
		List<Product> prodList = productRepository.findAll();
		System.out.println(prodList);
		assertEquals(3, prodList.size());
		assertEquals("Phone", prodList.get(0).getName());
	}
	
	@Test
	@DisplayName("get-product-with-non-existing-id-test")
	public void given_Product_With_Non_Existing_ID_Return_Optional_Empty() {
		productRepository.save(p1);
		assertThat(productRepository.findById(2).isEmpty());
	}

}
