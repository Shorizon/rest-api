package com.qa.java.productSQL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



//if code throws this exception, then we send the response to the client
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No product found with this ID")
public class ProductNotFoundException extends Exception {

	
}
