package com.qa.java.productSQL.service;

import java.util.List;

import com.qa.java.productSQL.dto.ProductDto;
import com.qa.java.productSQL.entity.Product;
import com.qa.java.productSQL.exception.ProductAlreadyExistsException;
import com.qa.java.productSQL.exception.ProductNotFoundException;

public interface ProductService {
	
	public List<Product> getAllProduct();
	public Product addProduct(Product product) throws ProductAlreadyExistsException;
	public Product getProductByID(int id) throws ProductNotFoundException;
	public List<Product> getProductByName(String name);
	public Product updateProduct(Product product) throws ProductNotFoundException;
	public Boolean deleteProduct(int id) throws ProductNotFoundException;
	public List<Product> getProductsByPrice(double price);
	public double getTotalPriceOfAllProducts();
	public List<Product> getProductByNameAndPrice(String name, double price);
	public Product updateProductDetails(int id, String name, double price) throws ProductNotFoundException;
	
	public List<ProductDto> getProductDetails();
}
