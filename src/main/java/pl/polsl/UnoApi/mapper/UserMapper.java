package pl.polsl.UnoApi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import pl.polsl.UnoApi.model.UserDto;
import pl.polsl.UnoApi.repository.dao.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User users);

    User userDtoToUser(UserDto userDto);

    List<UserDto> usersToUserDtos(List<User> users);
}
