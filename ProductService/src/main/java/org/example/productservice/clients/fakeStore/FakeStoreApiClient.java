package org.example.productservice.clients.fakeStore;

import org.example.productservice.dtos.FakeProductServiceDto;

public interface FakeStoreApiClient {

    FakeProductServiceDto getProductById(long id);

    FakeProductServiceDto[] getAllProducts();

    FakeProductServiceDto createProduct(FakeProductServiceDto fakeProductServiceDtoReq);

    FakeProductServiceDto updateProduct(long id,FakeProductServiceDto fakeProductServiceDtoReq);

    FakeProductServiceDto deleteProduct(long id);
}
