package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.ApiResponse;
import com.app.payload.UserDto;
import com.app.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User Controller", description = "User Management API's")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Operation(summary = "Create a User", description = "Creates a new User")
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto dto) {
		try {
			UserDto created = userService.createUser(dto);
			logger.info("Created:{} " + created);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);

		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Cause: " + e.getCause());
		}

	}

	@Operation(summary = "Update a User by ID", description = "Update a User object by specifying its ID.")
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto dto, @PathVariable("id") int id) {
		UserDto updateUser = userService.updateUser(dto, id);
		logger.info("Updated:{} " + updateUser);
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}

	@Operation(summary = "Retrieve a User by ID", description = "Get a User object by specifying its ID.")
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@Operation(summary = "Fetch all Users", description = "Fetches all User entities and their data from data source")
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllUsers() {
		try {
			List<UserDto> getAllUser = userService.getAllUsers();
			logger.info("Users:{} " + getAllUser);
			return ResponseEntity.status(HttpStatus.OK).body(getAllUser);
		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cause: " + e.getCause());
		}
	}

	@Operation(summary = "Delete a User by ID", description = "Delete a User object by specifying its ID.")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		logger.info("Deleted");
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Deleted With Id:" + id, true));
	}
}
