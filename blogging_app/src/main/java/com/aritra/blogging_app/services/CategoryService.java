package com.aritra.blogging_app.services;

import java.util.List;

import com.aritra.blogging_app.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	
	public void deleteCategory(int categoryId);
	
	public CategoryDto getCategoryById(int categoryId);
	
	public List<CategoryDto> getAllCategories();

}
