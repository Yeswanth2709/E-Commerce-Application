package org.example.productservice.services;

import org.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(long id);
    List<Product> getAllProducts();
}
