package org.example.productservice.controllers;

import jakarta.validation.Valid;
import org.example.productservice.dtos.CreateProductRequestDto;
import org.example.productservice.dtos.UpdateProductImageRequestDto;
import org.example.productservice.dtos.UpdateProductPriceRequestDto;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Product createProduct(@Valid @RequestBody CreateProductRequestDto requestDto) {
        return  productService.createProduct(requestDto.getTitle(),requestDto.getDescription(),requestDto.getImage(),requestDto.getPrice(),requestDto.getCategoryId());
    }

    @PatchMapping("/price/{id}")
    public Product updateProductPrice(@PathVariable("id") long id, @Valid @RequestBody UpdateProductPriceRequestDto requestDto) {
        return productService.updatePrice(id,requestDto.getPrice());
    }

    @PatchMapping("/image/{id}")
    public Product updateProductImage(@PathVariable("id") long id, @Valid @RequestBody UpdateProductImageRequestDto requestDto) {
        return productService.updateImage(id,requestDto.getImage());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") long id) {
        boolean result= productService.deleteProduct(id);
    }
}
