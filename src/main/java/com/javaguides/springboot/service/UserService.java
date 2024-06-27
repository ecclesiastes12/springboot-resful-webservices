package com.javaguides.springboot.service;

import java.util.List;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;

public interface UserService {

  // User createUser(User user);

  // User getUserById(Long userId);

  // List<User> getAllUsers();

  // User updateUser(User user);

  // void deleteUser(Long userId);

  // return type change from User object to Dto object
  UserDto createUser(UserDto user);

  UserDto getUserById(Long userId);

  List<UserDto> getAllUsers();

  UserDto updateUser(UserDto user);

  void deleteUser(Long userId);
}
