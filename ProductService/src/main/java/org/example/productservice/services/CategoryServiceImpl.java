package org.example.productservice.services;

import org.example.productservice.models.Category;
import org.example.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
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
        categoryRepository.deleteById(id);
        return true;
    }
}
