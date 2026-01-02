package org.example.productservice.services;

import org.example.productservice.clients.fakeStore.FakeStoreApiClient;
import org.example.productservice.dtos.FakeProductServiceDto;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;

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

    @Override
    public Product createProduct(String title, String description, String image, double price, long categoryId) {
        FakeProductServiceDto fakeProductServiceDtoReq=new FakeProductServiceDto();
        fakeProductServiceDtoReq.setTitle(title);
        fakeProductServiceDtoReq.setDescription(description);
        fakeProductServiceDtoReq.setImage(image);
        fakeProductServiceDtoReq.setPrice(price);
        FakeProductServiceDto fakeProduct = fakeStoreApiClient.createProduct(fakeProductServiceDtoReq);
        return convertDtoToProduct(fakeProduct);
    }

    @Override
    public Product updatePrice(long productId, double updatedPrice) {
        FakeProductServiceDto fakeProductServiceDtoReq=fakeStoreApiClient.getProductById(productId);
        fakeProductServiceDtoReq.setPrice(updatedPrice);
        FakeProductServiceDto fakeProductServiceDto = fakeStoreApiClient.updateProduct(productId,fakeProductServiceDtoReq);
        return convertDtoToProduct(fakeProductServiceDto);
    }

    @Override
    public Product updateImage(long productId, String updatedImage) {
        FakeProductServiceDto fakeProductServiceDtoReq=fakeStoreApiClient.getProductById(productId);
        fakeProductServiceDtoReq.setImage(updatedImage);
        FakeProductServiceDto fakeProductServiceDto = fakeStoreApiClient.updateProduct(productId,fakeProductServiceDtoReq);
        return convertDtoToProduct(fakeProductServiceDto);
    }

    @Override
    public boolean deleteProduct(long id) {
        FakeProductServiceDto fakeProductServiceDto=fakeStoreApiClient.deleteProduct(id);
        if (fakeProductServiceDto!=null){
            return true;
        }
        return false;
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
