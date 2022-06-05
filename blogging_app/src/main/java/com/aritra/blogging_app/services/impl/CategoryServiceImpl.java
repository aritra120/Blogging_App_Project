package com.aritra.blogging_app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aritra.blogging_app.entities.Category;
import com.aritra.blogging_app.payloads.CategoryDto;
import com.aritra.blogging_app.repositories.CategoryRepo;
import com.aritra.blogging_app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category obj = this.repo.save(category);
		return this.modelMapper.map(obj, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category upd = this.repo.findById(categoryId).get();
		upd.setCategoryName(categoryDto.getCategoryName());
		upd.setCategoryDescription(categoryDto.getCategoryDescription());
		this.repo.save(upd);
		return this.modelMapper.map(upd, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category = this.repo.findById(categoryId).get();
		this.repo.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = this.repo.findById(categoryId).get();
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> obj = this.repo.findAll();
		List<CategoryDto>categories = obj.stream()
				.map((cat)->this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return categories;
	}

}
