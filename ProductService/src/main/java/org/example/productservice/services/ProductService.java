package org.example.productservice.services;

import org.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getProductById(long id);
    Page<Product> getAllProducts(int PageSize, int pageNumber);
    Product createProduct(String title,String description,String image,double price,long categoryId);
    Product updatePrice(long productId,double updatedPrice);

    Product updateImage(long productId,String updatedImage);
    boolean deleteProduct(long id);
}
