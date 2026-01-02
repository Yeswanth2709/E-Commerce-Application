package org.example.productservice.dtos;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateProductPriceRequestDto {
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;
}
