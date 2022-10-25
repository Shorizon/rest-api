package com.qa.java.productSQL.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.java.productSQL.dto.ProductDto;
import com.qa.java.productSQL.entity.Product;
import com.qa.java.productSQL.exception.ProductAlreadyExistsException;
import com.qa.java.productSQL.exception.ProductNotFoundException;
import com.qa.java.productSQL.service.ProductServiceImpl;

@RestController
@RequestMapping("api/v1")
public class ProductController {

	@Autowired
	ProductServiceImpl productService;
	
	ResponseEntity<?> responseEntity;
	
	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts(){
		
		
		try {
			List<Product> productList = this.productService.getAllProduct();
			responseEntity = new ResponseEntity<>(productList, HttpStatus.OK);
			
			
		} catch(Exception e) {
			System.out.println("Some internal error has occured.. Please try again! :)");
		}
		
		return responseEntity;
	}
	
	  
	  @GetMapping("/products/{id}")
	  public ResponseEntity<?> getProductByID(@PathVariable("id") int id) throws ProductNotFoundException{
		
		  try {
			  
			  Product productList = this.productService.getProductByID(id);
			  responseEntity = new ResponseEntity<>(productList, HttpStatus.OK);
	  
			  
		  } catch (ProductNotFoundException e) {
			  throw e;
		  
		  }
		  
		  catch(Exception e) {
			  
			  responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		  }
		  
		  return responseEntity;
		  
	  }
	  
	  @PostMapping("/products")
	  public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) throws ProductAlreadyExistsException {
		  
		  try {
			  Product addedProduct = this.productService.addProduct(product);
			  System.out.println("Added Product: " + addedProduct);
			  responseEntity = new ResponseEntity<>(product, HttpStatus.CREATED);
			  
		  } catch(ProductAlreadyExistsException e) {
			  throw e;
			  
		  } catch(Exception e) {
			  e.printStackTrace();
			  responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			  
		  }
		  
		  return responseEntity;
	  }
	  
	  @PutMapping("/products")
	  public ResponseEntity<?> updateProduct(@RequestBody Product product) throws ProductNotFoundException{
		  
		  try {
			  
			  Product updatedProduct = this.productService.updateProduct(product);
			  responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
			  
		  } catch (ProductNotFoundException e) {
			  throw e;
		  } catch (Exception e) {
			  responseEntity = new ResponseEntity<>("Some internal error has occured.. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
			  e.printStackTrace();
		  }
		  
		  return responseEntity;
	  }
	  
	  @DeleteMapping("/products/{id}")
	  public ResponseEntity<?> deleteProduct(@PathVariable int id) throws ProductNotFoundException{
		  
		  try {
			  boolean status = this.productService.deleteProduct(id);
			  responseEntity = new ResponseEntity<>("Deleted product successfully !", HttpStatus.OK);
		  } catch (ProductNotFoundException e) {
			  throw e;
		  } catch (Exception e) {
			  responseEntity = new ResponseEntity<>("Some internal error has occured.. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
			  e.printStackTrace();
		  }
		  
		  return responseEntity;
		  
	  }
	  
	  @GetMapping("/products/price/{price}")
	  public ResponseEntity<?> findProductsByPrice(@PathVariable("price") double price){
		
			  try {
				  
				  List<Product> productListByPrice = this.productService.getProductsByPrice(price);
				  responseEntity = new ResponseEntity<>(productListByPrice, HttpStatus.OK);
			  } catch (Exception e) {
				  
				  responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			  }
			 
		  return responseEntity;
	 
	  }
	  
	  @GetMapping("/products/total_price")
	  public ResponseEntity<?> findTotalPriceOfAllProducts(){
		
			  try {
				  
				  double totalPriceValue = this.productService.getTotalPriceOfAllProducts();
				  responseEntity = new ResponseEntity<>(totalPriceValue, HttpStatus.OK);
			  } catch (Exception e) {
				  
				  responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			  }
			 
		  return responseEntity;
	 
	  }
	  
	  @GetMapping("/products/name/{name}/price/{price}")
	  public ResponseEntity<?> getProductByNameAndPrice(@PathVariable("name") String name, @PathVariable("price") double price){
		
			  try {
				  
				  List<Product> productListByNameAndPrice = this.productService.getProductByNameAndPrice(name, price);
				  responseEntity = new ResponseEntity<>(productListByNameAndPrice, HttpStatus.OK);
			  } catch (Exception e) {
				  
				  responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			  }
			 
		  return responseEntity;
	 
	  }
	  
	  @GetMapping("/products/name/{name}")
	  public ResponseEntity<?> getProductByName(@PathVariable("name") String name){
		
			  try {
				  
				  List<Product> productListByNameAndPrice = this.productService.getProductByName(name);
				  responseEntity = new ResponseEntity<>(productListByNameAndPrice, HttpStatus.OK);
			  } catch (Exception e) {
				  
				  responseEntity = new ResponseEntity<>("Some internal error has occured..", HttpStatus.INTERNAL_SERVER_ERROR);
			  }
			 
		  return responseEntity;
	 
	  }
	  
	  @PutMapping("/products/update_details")
	  public ResponseEntity<?> updateProductDetails(@RequestBody Product product) throws ProductNotFoundException{
		  
		  try {
			  
			  Product updatedProduct = this.productService.updateProductDetails(product.getId(), product.getName(), product.getPrice());
			  responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
			  
		  } catch (ProductNotFoundException e) {
			  throw e;
		  } catch (Exception e) {
			  responseEntity = new ResponseEntity<>("Some internal error has occured.. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
			  e.printStackTrace();
		  }
		  
		  return responseEntity;
	  }
	  
	  @GetMapping("/products/prod_dept_details")
	  public ResponseEntity<?> getAllProductDeptDetails(){
		  
		  try {
			  
			  List<ProductDto> productDtoList = this.productService.getProductDetails();
			  responseEntity = new ResponseEntity<>(productDtoList, HttpStatus.OK);
			  
		  } catch (Exception e) {
			  responseEntity = new ResponseEntity<>("Some internal error has occured.. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
			  e.printStackTrace();
		  }
		  
		  return responseEntity;
	  }
	
}
