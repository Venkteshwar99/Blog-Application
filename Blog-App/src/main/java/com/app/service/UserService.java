package com.app.service;

import java.util.List;

import com.app.payload.UserDto;

public interface UserService {

	public UserDto createUser(UserDto userDto);

	public UserDto updateUser(UserDto userDto, int id);

	public List<UserDto> getAllUsers();

	public UserDto getUserById(int id);

	public void deleteUser(int id);
}
