package org.example.productservice.services;

import org.example.productservice.clients.fakeStore.FakeStoreApiClient;
import org.example.productservice.configs.RestTemplateConfig;
import org.example.productservice.dtos.FakeProductServiceDto;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private final FakeStoreApiClient fakeStoreApiClient;
    public FakeStoreProductServiceImpl(FakeStoreApiClient fakeStoreApiClient) {
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public Product getProductById(long id) {
        FakeProductServiceDto productDto=fakeStoreApiClient.getProductById(id);
        return convertDtoToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeProductServiceDto[] productDtos=fakeStoreApiClient.getAllProducts();
        return Arrays.stream(productDtos).map(this::convertDtoToProduct).toList();
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
