package com.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.repository.UserRepository;
import com.javaguides.springboot.service.UserService;

import lombok.AllArgsConstructor;
import mapper.UserMapper;

/*CODE REFACTORED TO MATCH THE DTO OBJECT. CHECK UserServiceImpl1.java for the previous code */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  // @Override
  // public UserDto createUser(UserDto userDto) {
  // // convert UserDto into Use JPA Entity
  // User user = new User(
  // userDto.getId(),
  // userDto.getFirstName(),
  // userDto.getLastName(),
  // userDto.getEmail());
  // User savedUser = userRepository.save(user);

  // // convert User JPA Entity to UserDto
  // UserDto savedUserDto = new UserDto(
  // savedUser.getId(),
  // savedUser.getFirstName(),
  // savedUser.getLastName(),
  // savedUser.getEmail());

  // return savedUserDto;
  // }

  // same as the above code
  @Override
  public UserDto createUser(UserDto userDto) {
    // convert UserDto into Use JPA Entity
    User user = UserMapper.mapToUser(userDto);
    User savedUser = userRepository.save(user);

    // convert User JPA Entity to UserDto
    UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

    return savedUserDto;
  }

  @Override
  public UserDto getUserById(Long userId) {
    // NB here User object is used in the optional parameter because the return jpa
    // entity object returned is
    // of type User. You have to get the value first before it will be converted
    // into a dto
    Optional<User> optionalUser = userRepository.findById(userId);
    User user = optionalUser.get(); // store the return value from the repository

    return UserMapper.mapToUserDto(user);// convert the return Jpa entity object which is of type User into UserDto
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = userRepository.findAll();

    // List<UserDTO> userDTOs = new ArrayList<>();

    // for (User user : users) {
    // UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());
    // // Map other necessary fields
    // userDTOs.add(userDTO);
    // }
    // return userDTOs

    // the above code also works
    return users.stream().map(UserMapper::mapToUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto updateUser(UserDto user) {
    // get the existing user by id
    User existingUser = userRepository.findById(user.getId()).get();
    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setEmail(user.getEmail());
    User updatedUser = userRepository.save(existingUser);
    return UserMapper.mapToUserDto(updatedUser);
  }

  @Override
  public void deleteUser(Long userId) {
    userRepository.deleteById(userId);
  }

}
