package ma.website.blog.service;

import ma.website.blog.dto.CategoryDto;
import ma.website.blog.dto.PostDto;

import java.util.List;

public interface CategoryService {


    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategory(Integer categoryID);
    void deleteCategory(Integer categoryID);
    CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryID);
}
