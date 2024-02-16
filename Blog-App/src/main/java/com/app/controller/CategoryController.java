package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.ApiResponse;
import com.app.payload.CategoryDto;
import com.app.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Category Controller", description = "Category Management API's")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@Operation(summary = "Create a Category", description = "Creates a new Category")
	@PostMapping("/create")
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto dto) {
		try {
			CategoryDto created = categoryService.createCategory(dto);
			logger.info("Created:{} " + created);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);

		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Cause: " + e.getCause());
		}

	}

	@Operation(summary = "Update a Category by ID", description = "Update a Category object by specifying its ID.")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto dto, @PathVariable("id") int id) {
			CategoryDto update = categoryService.updateCategory(dto, id);
			logger.info("Updated:{} " + update);
			return ResponseEntity.status(HttpStatus.OK).body(update);
	}

	@Operation(summary = "Retrieve a Post by Category ID", description = "Get a Category object by specifying User ID.")
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") int id) {
		return ResponseEntity.ok( categoryService.getCategoryById(id));
	}
	
	@Operation(summary = "Fetch all Categories", description = "Fetches all Categories entities and their data from data source")
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllCategorys() {
		try {
			List<CategoryDto> getAllCategory = categoryService.getAllCategories();
			logger.info("Categorys:{} " + getAllCategory);
			return ResponseEntity.status(HttpStatus.OK).body(getAllCategory);
		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cause: " + e.getCause());
		}
	}

	@Operation(summary = "Delete a Category by ID", description = "Delete a Category object by specifying its ID.")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id) {
		try {
			categoryService.deleteCategory(id);
			logger.info("Deleted");
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category Deleted With Id:"+id,true));
		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cause: " + e.getCause());
		}
	}
	
	

}
