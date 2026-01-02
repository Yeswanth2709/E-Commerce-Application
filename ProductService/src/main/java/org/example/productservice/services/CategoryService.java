package org.example.productservice.services;

import org.example.productservice.models.Category;

import java.util.List;

public interface CategoryService {


    Category getCategoryById(Long id);
    List<Category> getCategories();
    Category createCategory(String name);
    Category updateCategory(long id,String name);
    boolean deleteCategory(long id);

}
