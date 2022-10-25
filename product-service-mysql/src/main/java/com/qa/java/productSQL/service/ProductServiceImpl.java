package com.qa.java.productSQL.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.java.productSQL.dto.ProductDto;
import com.qa.java.productSQL.entity.Product;
import com.qa.java.productSQL.exception.ProductAlreadyExistsException;
import com.qa.java.productSQL.exception.ProductNotFoundException;
import com.qa.java.productSQL.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();

	}

	@Override
	public Product getProductByID(int id) throws ProductNotFoundException{
	
		Optional<Product> productFoundByIdOptional = this.productRepository.findById(id);
		if (!productFoundByIdOptional.isPresent())
			throw new ProductNotFoundException();
		return productFoundByIdOptional.get();
	}
	
	@Override
	public Product addProduct(Product product) throws ProductAlreadyExistsException{
		
		Optional<Product> productFoundByIdOptional = this.productRepository.findById(product.getId());	
		if(productFoundByIdOptional.isPresent()) {
			throw new ProductAlreadyExistsException();
		}
		else {
			return this.productRepository.save(product);
		}
		
	}

	@Override
	public Product updateProduct(Product product) throws ProductNotFoundException {
		
		Optional<Product> productFoundByIdOptional = this.productRepository.findById(product.getId());	
		if(!productFoundByIdOptional.isPresent()) {
			throw new ProductNotFoundException();
		}
		else {
			return this.productRepository.save(product);
		}
	}

	@Override
	public Boolean deleteProduct(int id) throws ProductNotFoundException {
		
		boolean status = false;
		
		Optional<Product> productFoundByIdOptional = this.productRepository.findById(id);
		if(!productFoundByIdOptional.isPresent()) {
			throw new ProductNotFoundException();
		}
		else {
			this.productRepository.delete(productFoundByIdOptional.get());
			status = true;
		}
		
		return status;
	}

	@Override
	public List<Product> getProductsByPrice(double price) {
		
		return this.productRepository.findByPrice(price);
	}

	@Override
	public double getTotalPriceOfAllProducts() {
		
		return this.productRepository.totalPriceOfAllProducts();
	}

	@Override
	public List<Product> getProductByNameAndPrice(String name, double price) {

		return this.productRepository.findProductByNameAndPrice(name, price);
	}

	@Override
	public List<Product> getProductByName(String name) {
		
		return this.productRepository.findByName(name);
	}

	@Override
	public Product updateProductDetails(int id, String name, double price) throws ProductNotFoundException{
		Product updatedProduct = null;
		
		Optional<Product> findByIdOptional = this.productRepository.findById(id);
		if(!findByIdOptional.isPresent()) {
			throw new ProductNotFoundException();
		}
		else {
			int rows = this.productRepository.updateProductDetails(id, name, price);
			if (rows > 0) {
				updatedProduct = this.productRepository.findById(id).get();
			}
		}
		return updatedProduct;
	}

	@Override
	public List<ProductDto> getProductDetails() {
	
		
		return this.productRepository.findAll().stream().map(this::mapToProductDto).collect(Collectors.toList());
		
	}
	
	private ProductDto mapToProductDto(Product product) {
		return this.modelMapper.map(product, ProductDto.class);
	}

	

	

}
