package org.example.productservice.clients.fakeStore;

import org.example.productservice.dtos.FakeProductServiceDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Primary
@Component
public class FakeStoreApiClientWebClientImpl implements FakeStoreApiClient {

    private final WebClient webClient;
    private static final String BASE_URL = "https://fakestoreapi.com/products";

    public FakeStoreApiClientWebClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public FakeProductServiceDto getProductById(long id) {
        return webClient
                .get()
                .uri(BASE_URL + "/{id}",id).retrieve()
                .bodyToMono(FakeProductServiceDto.class).block();
    }

    @Override
    public FakeProductServiceDto[] getAllProducts() {
        return webClient
                .get()
                .uri(BASE_URL).retrieve()
                .bodyToMono(FakeProductServiceDto[].class).block();
    }
}
