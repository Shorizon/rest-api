package com.qa.java.productSQL.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.java.productSQL.entity.Product;
import com.qa.java.productSQL.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
	@Mock
	private ProductServiceImpl prodServices;

	@Autowired
	@InjectMocks
	private ProductController prodController;

	@Autowired
	MockMvc mockMvc;

	Product prod1;
	Product prod2;
	Product prod3;

	List<Product> prodList;

	@BeforeEach
	public void setUp() {
		/*
		 * Create the necessary instances
		 * Create dummy data
		 */
		prod1 = new Product(1, "Phone", 249.99);
		prod2 = new Product(2, "tvtest", 599.99);
		prod3 = new Product(3, "Wallettest", 12.99);
		prodList = Arrays.asList(prod1, prod2, prod3);

		mockMvc = MockMvcBuilders.standaloneSetup(prodController).build();
	}

	@AfterEach
	public void tearDown() {
		prod1 = prod2 = prod3 = null;
		prodList = null;

	}

	@Test
	@DisplayName("save-product-test")
	public void given_Product_To_Save_Product_Should_Return_Product_As_JSON_With_Status_Created() throws Exception {
		when(prodServices.addProduct(any())).thenReturn(prod1);
		mockMvc.perform(post("/api/v1/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(prod1)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name").value("Phone"));
	}

	@Test
	@DisplayName("get-product-test")
	public void given_GetAllProducts_Should_Return_List() throws Exception {
		when(prodServices.getAllProduct()).thenReturn(prodList);
		mockMvc.perform(get("/api/v1/products")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].name").value("tvtest"));
	}

	public static String asJsonString(Object obj) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		
		try {
			jsonStr = Obj.writeValueAsString(obj);
			System.out.println(jsonStr);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}


}
