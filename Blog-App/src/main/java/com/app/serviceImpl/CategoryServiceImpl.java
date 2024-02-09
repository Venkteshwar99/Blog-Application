package com.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Category;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payload.CategoryDto;
import com.app.repository.CategoryRepo;
import com.app.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category Category = dtoToCategoryEntity(categoryDto);
		Category savedCategory = categoryRepo.save(Category);
		return this.CategoryEntityToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updateCategory = categoryRepo.save(category);
		CategoryDto dtoToEntity = CategoryEntityToDto(updateCategory);
		return dtoToEntity;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> list = categoryRepo.findAll();
		List<CategoryDto> dtoToEntity = list.stream().map(Category -> this.CategoryEntityToDto(Category))
				.collect(Collectors.toList());
		return dtoToEntity;

	}

	@Override
	public CategoryDto getCategoryById(int id) {

		Category Category = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));

		CategoryDto dtoToEntity = CategoryEntityToDto(Category);
		return dtoToEntity;
	}

	@Override
	public void deleteCategory(int id) {

		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
		categoryRepo.delete(category);

	}

	private Category dtoToCategoryEntity(CategoryDto dto) {
		Category Category = mapper.map(dto, Category.class);

		// Category Category = new Category();
		// Category.setId(dto.getId());
		// Category.setEmail(dto.getEmail());
		// Category.setName(dto.getName());
		// Category.setPassword(dto.getPassword());
		// Category.setAbout(dto.getAbout());
		return Category;
	}

	private CategoryDto CategoryEntityToDto(Category Category) {

		CategoryDto CategoryDto = mapper.map(Category, CategoryDto.class);

		// CategoryDto dto = new CategoryDto();
		// dto.setId(Category.getId());
		// dto.setEmail(Category.getEmail());
		// dto.setName(Category.getName());
		// dto.setPassword(Category.getPassword());
		// dto.setAbout(Category.getAbout());
		return CategoryDto;
	}

}
