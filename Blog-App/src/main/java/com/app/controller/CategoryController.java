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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody CategoryDto dto) {
		try {
			CategoryDto created = categoryService.createCategory(dto);
			logger.info("Created:{} " + created);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);

		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Cause: " + e.getCause());
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody CategoryDto dto, @PathVariable("id") int id) {
			CategoryDto update = categoryService.updateCategory(dto, id);
			logger.info("Updated:{} " + update);
			return ResponseEntity.status(HttpStatus.OK).body(update);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		return ResponseEntity.ok( categoryService.getCategoryById(id));
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllUsers() {
		try {
			List<CategoryDto> getAllUser = categoryService.getAllCategories();
			logger.info("Users:{} " + getAllUser);
			return ResponseEntity.status(HttpStatus.OK).body(getAllUser);
		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cause: " + e.getCause());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
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
