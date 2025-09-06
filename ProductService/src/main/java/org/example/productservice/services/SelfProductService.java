package org.example.productservice.services;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Primary
@Service
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public SelfProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String categoryName) {
        Category category = categoryService.createCategory(categoryName);
        Product product = new Product();
        product.setCategory(category);
        product.setTitle(title);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        return productRepository.save(product);

    }

    @Override
    public Product updatePrice(long productId, double updatedPrice) {
        return null;
    }

    @Override
    public Product updateImage(long productId, String updatedImage) {
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }
}
