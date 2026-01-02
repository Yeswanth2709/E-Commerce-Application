package org.example.productservice.clients.fakeStore;

import org.example.productservice.dtos.FakeProductServiceDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
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

    @Override
    public FakeProductServiceDto createProduct(FakeProductServiceDto fakeProductServiceDtoReq) {
        return restTemplate.postForEntity(BASE_URL,fakeProductServiceDtoReq,FakeProductServiceDto.class).getBody();
    }

    @Override
    public FakeProductServiceDto updateProduct(long id, FakeProductServiceDto fakeProductServiceDtoReq) {
        return updateForEntity(BASE_URL+"/{id}",fakeProductServiceDtoReq,FakeProductServiceDto.class,id).getBody();
    }

    @Override
    public FakeProductServiceDto deleteProduct(long id) {
        return deleteForEntity(BASE_URL+"/{id}",FakeProductServiceDto.class,id).getBody();
    }

    private <T> ResponseEntity<T> updateForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }
    private  <T> ResponseEntity<T> deleteForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.DELETE, requestCallback, responseExtractor, uriVariables);
    }
}
