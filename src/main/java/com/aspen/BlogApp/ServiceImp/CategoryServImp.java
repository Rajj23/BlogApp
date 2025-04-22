package com.aspen.BlogApp.ServiceImp;

import com.aspen.BlogApp.dto.CategoryDto;
import com.aspen.BlogApp.model.Category;
import com.aspen.BlogApp.repo.CategoryRepo;
import com.aspen.BlogApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServImp implements CategoryService {

    @Autowired
    private CategoryRepo cate;

    @Autowired
    private ModelMapper model;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = DtoToCategory(categoryDto);
        Category savedCategory = cate.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,int id){
        Category category = cate.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category category1 = cate.save(category);
        CategoryDto updateCategory = categoryToDto(category1);
        return updateCategory;
    }

    @Override
    public CategoryDto getCategoryById(int id){
        Category category = this.cate.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found: "+ id));
        return this.categoryToDto(category);
    }

    public List<CategoryDto> getAllCategory(){
        List<Category> category = this.cate.findAll();
        List<CategoryDto> categoryDtos = category.stream().map(cr -> this.categoryToDto(cr)).collect(Collectors.toList());
        return categoryDtos;
    }

    public void deleteCategory(int id){
        Category category = this.cate.findById(id).orElseThrow(()->new RuntimeException("Category not found by: "+ id));
        this.cate.delete(category);
    }


    public Category DtoToCategory(CategoryDto categoryDto){
        Category category = this.model.map(categoryDto,Category.class);
        return category;
    }

    public CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = this.model.map(category,CategoryDto.class);
        return categoryDto;
    }

}
