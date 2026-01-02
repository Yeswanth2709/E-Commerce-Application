package org.example.productservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCategoryRequestDto {
    @NotBlank(message = "category name must not be blank")
    private String name;
}
