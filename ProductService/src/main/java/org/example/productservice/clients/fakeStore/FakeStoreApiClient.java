package org.example.productservice.clients.fakeStore;

import org.example.productservice.dtos.FakeProductServiceDto;
import org.springframework.stereotype.Component;

public interface FakeStoreApiClient {

    FakeProductServiceDto getProductById(long id);

    FakeProductServiceDto[] getAllProducts();
}
