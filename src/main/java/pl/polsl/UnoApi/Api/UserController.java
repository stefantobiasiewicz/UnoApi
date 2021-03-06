package pl.polsl.UnoApi.Api;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.Api.exceptions.ObjectExistException;
import pl.polsl.UnoApi.genApi.UserApi;
import pl.polsl.UnoApi.service.GameService;
import pl.polsl.UnoApi.mapper.UserMapper;
import pl.polsl.UnoApi.model.UserDto;
import pl.polsl.UnoApi.repository.UserRepository;
import pl.polsl.UnoApi.repository.dao.User;
import pl.polsl.UnoApi.validator.Validator;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
public class UserController implements UserApi {

    UserRepository userRepository;
    UserMapper userMapper;
    Validator validator;

    GameService gameService;

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return ResponseEntity.ok(userMapper.userToUserDto(userRepository.getById(id)));
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        var list = userMapper.usersToUserDtos(userRepository.findAll());
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        log.info("UserController -> addUser {}", userDto);
        var user = userMapper.userDtoToUser(userDto);
        validator.userValid(user);
        try{
            User saved = userRepository.save(user);
            return ResponseEntity.ok(userMapper.userToUserDto(saved));
        }
        catch (Exception ex){
            throw new ObjectExistException("Object already exist");
        }
    }
}
