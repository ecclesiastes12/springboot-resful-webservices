package com.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.exception.EmailAlreadyExistException;
import com.javaguides.springboot.exception.ResourceNotFounceException;
import com.javaguides.springboot.mapper.AutoUserMapper;
import com.javaguides.springboot.mapper.UserMapper;
import com.javaguides.springboot.repository.UserRepository;
import com.javaguides.springboot.service.UserService;

import lombok.AllArgsConstructor;

/*CODE REFACTORED TO MAP JPA ENTITY OBJECT TO DTO OBJECT AND VICE VERSA USING MODELMAPPER
WHICH IS CONFIGURED AS A BEAN IN SpringbootResfulWebservicesApplication.java.
 CHECK UserServiceImpl2.java for the code before using modelMapper*/
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  private ModelMapper modelMapper;

  // @Override
  // public UserDto createUser(UserDto userDto) {
  // // convert UserDto into Use JPA Entity
  // // User user = UserMapper.mapToUser(userDto);

  // // model mapper is used to convert UserDTO entity to JPA entity
  // // parameter 1 takes the object that you want to convert
  // // pareamter 2 takes the name of the entity class you are converting to
  // User user = modelMapper.map(userDto, User.class);
  // User savedUser = userRepository.save(user);

  // // convert User JPA Entity to UserDto
  // // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

  // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

  // return savedUserDto;
  // }

  @Override
  public UserDto createUser(UserDto userDto) {
    // convert UserDto into Use JPA Entity
    // User user = UserMapper.mapToUser(userDto);

    // model mapper is used to convert UserDTO entity to JPA entity
    // parameter 1 takes the object that you want to convert
    // pareamter 2 takes the name of the entity class you are converting to
    // User user = modelMapper.map(userDto, User.class);

	 //get user by email
	  Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
	  
	  //check if email already exist
	  if(optionalUser.isPresent()) {
		  //throws exception
		  throw new EmailAlreadyExistException("Email Already Exist for User");
	  }
	  
    // code refactored using mapstruct to convert DTO object(UserDto) to JPA Entity
    // object(User)
    User user = AutoUserMapper.MAPPER.mapToUser(userDto);

    User savedUser = userRepository.save(user);

    // convert User JPA Entity to UserDto
    // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

    // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

    // code refactored using mapstruct to convert JPA Entity object(User ) to DTO
    // object(UserDto)
    UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

    return savedUserDto;
  }

//  @Override
//  public UserDto getUserById(Long userId) {
//    // NB here User object is used in the optional parameter because the return jpa
//    // entity object returned is
//    // of type User. You have to get the value first before it will be converted
//    // into a dto
//    Optional<User> optionalUser = userRepository.findById(userId);
//    User user = optionalUser.get(); // store the return value from the repository
//
//    // return UserMapper.mapToUserDto(user);// convert the return Jpa entity object
//    // which is of type User into UserDto
//
//    // return modelMapper.map(user, UserDto.class);
//
//    // code refactored using mapstruct to convert JPA Entity object(User ) to DTO
//    // object(UserDto)
//    return AutoUserMapper.MAPPER.mapToUserDto(user);
//  }
  
  
  //code modified with custom exception
  @Override
  public UserDto getUserById(Long userId) {
    //Optional<User> optionalUser changed to User user
    User user = userRepository.findById(userId).orElseThrow(
    		//throws exception if user id is not found
    		() -> new ResourceNotFounceException("User", "id", userId) 
    		);
   // User user = optionalUser.get(); // store the return value from the repository
    return AutoUserMapper.MAPPER.mapToUserDto(user);
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

    // // the above code also works
    // return users.stream().map(UserMapper::mapToUserDto)
    // .collect(Collectors.toList());

    // return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
    // .collect(Collectors.toList());

    // code refactored using mapstruct to convert JPA Entity object(User ) to DTO
    // object(UserDto)
    return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
        .collect(Collectors.toList());
  }

//  @Override
//  public UserDto updateUser(UserDto user) {
//    // get the existing user by id
//    User existingUser = userRepository.findById(user.getId()).get();
//    existingUser.setFirstName(user.getFirstName());
//    existingUser.setLastName(user.getLastName());
//    existingUser.setEmail(user.getEmail());
//    User updatedUser = userRepository.save(existingUser);
//    // return UserMapper.mapToUserDto(updatedUser);
//
//    // return modelMapper.map(updatedUser, UserDto.class);
//
//    // code refactored using mapstruct to convert JPA Entity object(User ) to DTO
//    // object(UserDto)
//    return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
//  }
  
  
  //code modified with resource not found exception 
  @Override
  public UserDto updateUser(UserDto user) {
    // get the existing user by id
    User existingUser = userRepository.findById(user.getId()).orElseThrow(
    		//throws exception if user id is not found
    		() -> new ResourceNotFounceException("User", "id", user.getId()) 
    		);
    existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    existingUser.setEmail(user.getEmail());
    User updatedUser = userRepository.save(existingUser);
    // return UserMapper.mapToUserDto(updatedUser);

    // return modelMapper.map(updatedUser, UserDto.class);

    // code refactored using mapstruct to convert JPA Entity object(User ) to DTO
    // object(UserDto)
    return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
  }

  @Override
  public void deleteUser(Long userId) {
	  User existingUser = userRepository.findById(userId).orElseThrow(
	    		//throws exception if user id is not found
	    		() -> new ResourceNotFounceException("User", "id", userId) 
	    		);
    userRepository.deleteById(userId);
  }

}
