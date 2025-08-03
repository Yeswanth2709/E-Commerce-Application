package org.example.productservice.clients.fakeStore;

import org.example.productservice.dtos.FakeProductServiceDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClientRestTemplateImpl implements FakeStoreApiClient {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://fakestoreapi.com/products";
    public FakeStoreApiClientRestTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FakeProductServiceDto getProductById(long id) {
        return restTemplate.getForEntity(BASE_URL+"/{id}", FakeProductServiceDto.class, id).getBody();
    }

    public FakeProductServiceDto[] getAllProducts() {
        return restTemplate.getForObject(BASE_URL, FakeProductServiceDto[].class);
    }
}
