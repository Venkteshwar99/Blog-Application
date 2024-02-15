package com.app.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.config.AppConstants;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payload.UserDto;
import com.app.repository.RoleRepo;
import com.app.repository.UserRepo;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserRepo userRepo;

	@Autowired ModelMapper mapper;

	@Autowired PasswordEncoder passwordEncoder;

	@Autowired RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUserEntity(userDto);
		User savedUser = userRepo.save(user);
		return this.userEntityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());

		User updateUser = userRepo.save(user);
		UserDto dtoToEntity = userEntityToDto(updateUser); 
		return dtoToEntity;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> list = userRepo.findAll();
		List<UserDto> dtoToEntity = list.stream().map(user->this.userEntityToDto(user)).collect(Collectors.toList()); 
		return dtoToEntity;

	}

	@Override
	public UserDto getUserById(int id) {

		User user =userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id", id));

		UserDto dtoToEntity = userEntityToDto(user); 
		return dtoToEntity;
	}

	@Override
	public void deleteUser(int id) {

		User user =userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id", id));
		userRepo.delete(user);

	}


	private User dtoToUserEntity(UserDto dto) {
		User user = mapper.map(dto, User.class);

		//		User user = new User();
		//		user.setId(dto.getId());
		//		user.setEmail(dto.getEmail());
		//		user.setName(dto.getName());
		//		user.setPassword(dto.getPassword());
		//		user.setAbout(dto.getAbout());
		return user;
	}

	private UserDto userEntityToDto(User user) {

		UserDto userDto = mapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registereUser(UserDto userDto) {

		User user = mapper.map(userDto, User.class);
		String password = user.getPassword();
		System.out.println("password :-->"+password);
		user.setPassword(passwordEncoder.encode(password));
		Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);

		User save = userRepo.save(user);

		return mapper.map(save, UserDto.class);
	}

}
