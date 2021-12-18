package pl.polsl.UnoApi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.UnoApi.repository.LoginRepository;
import pl.polsl.UnoApi.repository.UserRepository;
import pl.polsl.UnoApi.repository.dao.Login;
import pl.polsl.UnoApi.repository.dao.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    UserRepository userRepository;
    LoginRepository loginRepository;

    public User registerNewUser(String login, String passord){
        User newUser = new User();
        newUser.setLogin(login);
        User savedUser = userRepository.save(newUser);

        Login newLogin = new Login();
        newLogin.setUser(savedUser);
        newLogin.setPassword(passord);
        loginRepository.save(newLogin);

        return newUser;
    }

    public User login(String login, String passord){
        Optional<Login> userLogin = loginRepository.findByUserLoginAndPassword(login, passord);
        return userLogin.map(Login::getUser).orElse(null);
    }


    public void changeUserData(Long userId, String login, String passord){
        userRepository.findById(userId).ifPresent(
                user -> {
                    loginRepository.deleteByUser(user);

                    user.setLogin(login);
                    user = userRepository.save(user);

                    Login newLogin = new Login();
                    newLogin.setUser(user);
                    newLogin.setPassword(passord);
                    loginRepository.save(newLogin);
                }
        );
    }
}
