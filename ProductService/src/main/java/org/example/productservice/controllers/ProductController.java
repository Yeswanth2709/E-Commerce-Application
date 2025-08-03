package org.example.productservice.controllers;

import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
