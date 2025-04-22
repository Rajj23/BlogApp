package com.aspen.BlogApp.Controller;

import com.aspen.BlogApp.dto.CategoryDto;
import com.aspen.BlogApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("home/api/category")
public class CategoryController {

    @Autowired
    private CategoryService cService;

    @PostMapping("creates")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto cDto){
        CategoryDto newCategory = this.cService.createCategory(cDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("updates/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryInController(@RequestBody CategoryDto cDto, @PathVariable("categoryId")int id){
        CategoryDto categoryDto = this.cService.updateCategory(cDto,id);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("get/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryByIdController(@PathVariable("categoryId") int id){
        CategoryDto categoryDto = this.cService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategoryInController(){
        List<CategoryDto> categoryDto = this.cService.getAllCategory();
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @DeleteMapping("delete/{categoryId}")
    public void deleteCategoryInController(@PathVariable("categoryId") int id){
        this.cService.deleteCategory(id);
    }

}
