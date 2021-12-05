package pl.polsl.UnoApi.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.api.UserApi;
import pl.polsl.UnoApi.model.UserDto;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<UserDto> getUsers() {
        UserDto user = new UserDto()
                .login("stefan")
                .password("iksde");
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        return UserApi.super.addUser(userDto);
    }
}
