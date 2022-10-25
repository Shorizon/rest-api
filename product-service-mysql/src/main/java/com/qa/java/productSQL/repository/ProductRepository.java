package com.qa.java.productSQL.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.java.productSQL.entity.Product;

@Repository
@Transactional //NEEDED FOR MODIFYING
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	
	//FIND PRODUCT BY NAME (change later to category once that property is added
	List<Product> findByName(String name);
	
	
	//FIND PRODUCT BY PRICE
	@Query("select p from Product p where p.price <= :price")
	List<Product> findByPrice(double price);
	
	//FIND TOTAL PRICE OF ALL PRODUCTS
	@Query(value = "select sum(product_price) from product_details", nativeQuery = true)
	Double totalPriceOfAllProducts();
	
	//FIND PRODUCT BY 2 PARAMETERS
	//THIS WAY IS DEPENDENT ON THE DATABASE AS IT USES TABLE NAMES AND COLUMN NAMES, IT IS BETTER TO BE INDEPENDENT OF THESE.
	/*
	 * @Query(value =
	 * "select * from product_details where product_name = :name and product_price = :price"
	 * , nativeQuery = true) List<Product> findProductByNameAndPrice(String name,
	 * double price);
	 */
	
	//THIS IS THE JPQL WAY OF DOING IT WHICH MAKES IT INDEPENDENT OF TABLE AND COLUMN NAMES USING THE PRODUCT OBJECT ITSELF AND ITS PROPERTIES
	@Query("select p from Product p where p.name = :name and p.price <= :price")
	List<Product> findProductByNameAndPrice(String name, double price);
	
	//UPDATING
	@Modifying //FOR UPDATE AND DELETE QUERIES
	@Query("update Product p set p.name = :name , p.price = :price where p.id = :id")
	int updateProductDetails(int id, String name, double price);
}
