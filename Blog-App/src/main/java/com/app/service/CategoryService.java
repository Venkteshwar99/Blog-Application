package com.app.service;

import java.util.List;

import com.app.payload.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, int id);

	public List<CategoryDto> getAllCategories();

	public CategoryDto getCategoryById(int id);

	public void deleteCategory(int id);
	
}
