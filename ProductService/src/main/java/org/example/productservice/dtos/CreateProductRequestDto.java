package org.example.productservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductRequestDto {
    @NotBlank(message = "Title must not be blank")
    private String title;
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;
    @NotBlank(message = "Description must not be blank")
    private String description;
    @NotBlank(message = "Image must not be blank")
    private String image;
    @NotNull(message = "CategoryId must not be null")
    private long categoryId;
}
