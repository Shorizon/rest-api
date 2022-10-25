package com.qa.java.productSQL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Employee with this ID already exists..")
public class ProductAlreadyExistsException extends Exception {
	
	

}
