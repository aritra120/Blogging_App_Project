package com.aritra.blogging_app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aritra.blogging_app.entities.User;
import com.aritra.blogging_app.exception.ResourceNotFoundException;
import com.aritra.blogging_app.payloads.UserDto;
import com.aritra.blogging_app.repositories.UserRepo;
import com.aritra.blogging_app.services.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		userRepo.save(user);
		return this.userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).get();
		if(user == null) {
			throw new ResourceNotFoundException(" User ", " Id ", userId);
		}
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updated = userRepo.save(user);
		return this.userToDto(updated);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).get();
		if(user == null) {
			throw new ResourceNotFoundException(" User ", " Id ", userId);
		}
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto>dtos = users.stream()
				.map(user-> this.userToDto(user)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepo.deleteById(userId);
		
	}

	private User dtoToUser (UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		return user;
	}
	
	private UserDto userToDto (User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}
}
