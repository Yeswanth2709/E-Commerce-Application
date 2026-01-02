package org.example.productservice.dtos;

import lombok.Data;

@Data
public class CreateProductRequestDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private long categoryId;
}
