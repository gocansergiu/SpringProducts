package com.products.springProducts.controller;

import com.products.springProducts.exception.ProductInternalErrorException;
import com.products.springProducts.exception.ProductNotAcceptableException;
import com.products.springProducts.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseStatusException exception(ProductNotFoundException exception) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not Found.");
    }

    @ExceptionHandler(value = ProductNotAcceptableException.class)
    public ResponseStatusException exception(ProductNotAcceptableException exception) {
        return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Product ID already exists.");
    }

    @ExceptionHandler(value = ProductInternalErrorException.class)
    public ResponseStatusException exception(ProductInternalErrorException exception) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server side error.");
    }
}
