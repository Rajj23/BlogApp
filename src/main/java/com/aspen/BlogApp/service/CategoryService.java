package com.aspen.BlogApp.service;

import com.aspen.BlogApp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto,int id);
    public CategoryDto getCategoryById(int id);
    public List<CategoryDto> getAllCategory();
    public void deleteCategory(int id);

}
