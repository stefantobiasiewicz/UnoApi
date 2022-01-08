package pl.polsl.UnoApi.Api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.genApi.LoginApi;
import pl.polsl.UnoApi.mapper.UserMapper;
import pl.polsl.UnoApi.model.ChangeUserDataDto;
import pl.polsl.UnoApi.model.LoginUserDto;
import pl.polsl.UnoApi.model.UserDto;
import pl.polsl.UnoApi.service.LoginService;

@RestController
@AllArgsConstructor
public class LoginController implements LoginApi {

    LoginService loginService;
    UserMapper userMapper;

    @Override
    public ResponseEntity<Void> changeData(ChangeUserDataDto changeUserDataDto) {
        try{
            loginService.changeUserData(
                    Long.valueOf(changeUserDataDto.getUserId()),
                    changeUserDataDto.getLogin().getLogin(),
                    changeUserDataDto.getLogin().getPassword()
            );
            return ResponseEntity.ok().build();
        }
        catch (Exception ex){
            return ResponseEntity.status(400).build();
        }

    }

    @Override
    public ResponseEntity<UserDto> login(LoginUserDto loginUserDto) {
        var user = loginService.login(loginUserDto.getLogin(), loginUserDto.getPassword());
        if(user != null){
            return ResponseEntity.ok(userMapper.userToUserDto(user));
        }
        return ResponseEntity.status(400).build();
    }

    @Override
    public ResponseEntity<UserDto> registerUser(LoginUserDto loginUserDto) {
        try{
            var user = loginService.registerNewUser(loginUserDto.getLogin(), loginUserDto.getPassword());
            return ResponseEntity.ok(userMapper.userToUserDto(user));
        }
        catch (Exception ex){
            return ResponseEntity.status(400).build();
        }
    }
}
