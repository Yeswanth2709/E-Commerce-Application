package org.example.productservice.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(long productId) {
        super("Product not found with id: " + productId);
    }
}
