package pl.polsl.UnoApi.validator;

import org.springframework.stereotype.Component;
import pl.polsl.UnoApi.Api.exceptions.ObjectValidException;
import pl.polsl.UnoApi.repository.dao.User;

@Component
public class Validator {

    public void userValid(User user){
        if(user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().isEmpty())
            throw new ObjectValidException("User.login field corrupted");
        if(user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().isEmpty())
            throw new ObjectValidException("User.password field corrupted");
    }

}
