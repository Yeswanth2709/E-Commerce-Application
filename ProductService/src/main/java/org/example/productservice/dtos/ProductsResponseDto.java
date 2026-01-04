package org.example.productservice.dtos;

import lombok.Data;
import org.example.productservice.models.Product;
import org.springframework.data.domain.Page;
@Data
public class ProductsResponseDto {
    private Page<Product> productList;
    private Response response;
}
