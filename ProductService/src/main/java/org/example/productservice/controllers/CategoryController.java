package org.example.productservice.controllers;

import jakarta.validation.Valid;
import org.example.productservice.dtos.CreateCategoryRequestDto;
import org.example.productservice.dtos.UpdateCategoryRequestDto;
import org.example.productservice.models.Category;
import org.example.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }



    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public Category createCategory(@Valid  @RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.createCategory(requestDto.getName());
    }

    @PatchMapping("/{id}")
    public Category updateCategory(@PathVariable long id, @Valid @RequestBody UpdateCategoryRequestDto requestDto) {
        return categoryService.updateCategory(id, requestDto.getName());
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable long id) {
        return categoryService.deleteCategory(id);
    }

}
