package org.example.productservice.controllers;

import jakarta.validation.Valid;
import org.example.productservice.components.AuthUtils;
import org.example.productservice.dtos.*;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AuthUtils authUtils;
    public ProductController(ProductService productService,AuthUtils authUtils) {
        this.productService = productService;
        this.authUtils=authUtils;
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @GetMapping()
    public ResponseEntity<ProductsResponseDto> getAllProducts(@RequestParam(value="pageSize",defaultValue = "10") int pageSize,
                                                              @RequestParam(value="pageNum",defaultValue = "0") int pageNum) {
        ProductsResponseDto responseDto = new ProductsResponseDto();
        responseDto.setProductList(productService.getAllProducts(pageSize,pageNum));
        responseDto.setResponse(Response.getSuccessResponse());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductRequestDto requestDto,@RequestHeader("Auth") String token) {
        if(!authUtils.validateToken(token)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Product product = productService.createProduct(requestDto.getTitle(),requestDto.getDescription(),requestDto.getImage(),requestDto.getPrice(),requestDto.getCategoryId());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
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
