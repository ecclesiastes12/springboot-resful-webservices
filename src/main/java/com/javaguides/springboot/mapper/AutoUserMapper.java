package com.javaguides.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.javaguides.springboot.dto.UserDto;

import com.javaguides.springboot.entity.User;

@Mapper
public interface AutoUserMapper {

  /*
   * Define mapping method for mapping jpa entity object to dto object and vice
   * versa. With both method in place as defined below, you don't need to write
   * code
   * to implement the method. Mapstruct will automatically generated to implement
   * the method at a compilation time
   */

  // provide the implementation of the mapping at runtime
  AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

  /*
   * mapstruct method that convert JPA Entity Object to DTO Object
   * 
   * @Mapping(source = "email", target = "emailAddress") // use this line of
   * code only if the JPA entity object property or field name
   * is different from the property or field name used in the DTO object class. eg
   * in
   * User.java there is field name email whiles in the UserDto.java the field
   * name is emailAddress
   */
  // @Mapping(source = "email", target = "emailAddress")
  UserDto mapToUserDto(User user);

  // // mapstruct method that convert DTO object to JPA Entity Object
  User mapToUser(UserDto userDto);
}
