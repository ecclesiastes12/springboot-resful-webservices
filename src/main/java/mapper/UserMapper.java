package mapper;

import java.util.List;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;

/* the purpose of this class is to convert Dto objects to Jpa entity object and vice versa */
public class UserMapper {

  // static method that map or convert User JPA entity object to UserDto
  public static UserDto mapToUserDto(User user) {
    UserDto userDto = new UserDto(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail());

    return userDto;
  }

  // static method that map or convert UserDto to User JPA entity object

  public static User mapToUser(UserDto userDto) {
    User user = new User(
        userDto.getId(),
        userDto.getFirstName(),
        userDto.getLastName(),
        userDto.getEmail());
    return user;
  }

}
