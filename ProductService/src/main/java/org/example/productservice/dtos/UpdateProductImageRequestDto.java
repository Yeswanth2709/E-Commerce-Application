package org.example.productservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProductImageRequestDto {
    @NotBlank(message = "image must not be blank")
    private String image;
}
