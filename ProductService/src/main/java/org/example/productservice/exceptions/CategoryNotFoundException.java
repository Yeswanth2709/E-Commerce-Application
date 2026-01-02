package org.example.productservice.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(long categoryId) {
        super("Category not found with id: " + categoryId);
    }
}
