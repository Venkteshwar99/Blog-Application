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

import com.app.payload.UserDto;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserDto dto) {
		try {
			UserDto created = userService.createUser(dto);
			logger.info("Created:{} " + created);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);

		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Cause: " + e.getCause());
		}

	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@RequestBody UserDto dto, @PathVariable("id") int id) {
		try {
			UserDto updateUser = userService.updateUser(dto, id);
			logger.info("Updated:{} " + updateUser);
			return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Cause: " + e.getCause());
		}

	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		try {
			UserDto getUser = userService.getUserById(id);
			logger.info("User:{} " + getUser);
			return ResponseEntity.status(HttpStatus.CREATED).body(getUser);
		} catch (Exception e) {
			logger.info("Error:{} " + e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.OK).body("Cause: " + e.getCause());
		}
	}

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

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		try {
			userService.deleteUser(id);
			logger.info("Deleted");
			return ResponseEntity.status(HttpStatus.OK).body("Deleted User With Id: " + id);
		} catch (Exception e) {
			logger.info("Error:{} " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cause: " + e.getCause());
		}
	}
}
