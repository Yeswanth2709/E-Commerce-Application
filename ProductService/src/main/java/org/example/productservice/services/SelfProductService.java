package org.example.productservice.services;

import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return productRepository
                .findById(id)
                .orElseThrow(()->new ProductNotFoundException(id));
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNumber) {
        pageSize = Math.min(pageSize,10);
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("price").descending()));
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
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
        Product product = getProductById(productId);
        product.setPrice(updatedPrice);
        return productRepository.save(product);
    }

    @Override
    public Product updateImage(long productId, String updatedImage) {
        Product product = getProductById(productId);
        product.setImage(updatedImage);
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(long id) {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
        return true;
    }
}
