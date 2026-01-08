package org.example.productservice.services;

import org.example.productservice.clients.fakeStore.FakeStoreApiClient;
import org.example.productservice.dtos.FakeProductServiceDto;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Primary
@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private final FakeStoreApiClient fakeStoreApiClient;
    private RedisTemplate<String, Object> redisTemplate;
    public FakeStoreProductServiceImpl(FakeStoreApiClient fakeStoreApiClient, RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoreApiClient = fakeStoreApiClient;
        this.redisTemplate=redisTemplate;
    }

    @Override
    public Product getProductById(long id) {
        Product product = (Product) this.redisTemplate.opsForHash().get("PRODUCTS", "products_" + id);
        if(product != null){
            return product;
        }
        FakeProductServiceDto productDto=fakeStoreApiClient.getProductById(id);
        Product p = convertDtoToProduct(productDto);
        this.redisTemplate.opsForHash().put("PRODUCTS", "products_" + id, p);
        return p;

    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNumber)  {
        FakeProductServiceDto[] productDtos=fakeStoreApiClient.getAllProducts();
        List<Product> products= Arrays.stream(productDtos).map(this::convertDtoToProduct).toList();
        return null;
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
