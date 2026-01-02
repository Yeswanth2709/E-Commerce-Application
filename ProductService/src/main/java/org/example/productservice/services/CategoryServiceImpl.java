package org.example.productservice.services;

import org.example.productservice.exceptions.CategoryNotFoundException;
import org.example.productservice.models.Category;
import org.example.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(()->new CategoryNotFoundException(id));
    }


    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(long id, String name) {
        Category category = getCategoryById(id);
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public boolean deleteCategory(long id) {
        if(!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
