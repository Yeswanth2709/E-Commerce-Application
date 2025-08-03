package org.example.productservice.services;

import org.example.productservice.configs.RestTemplateConfig;
import org.example.productservice.dtos.FakeProductServiceDto;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        FakeProductServiceDto productDto =restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceDto.class);
        return convertDtoToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        //List<FakeProductServiceDto> productDtos =restTemplate.getForObject("http://localhost:8080/products", List.class);
        return List.of();
    }

    private Product convertDtoToProduct(FakeProductServiceDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
