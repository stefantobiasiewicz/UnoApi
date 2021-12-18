package pl.polsl.UnoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.UnoApi.repository.dao.Login;
import pl.polsl.UnoApi.repository.dao.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Override
    Optional<Login> findById(Long aLong);

    Optional<Login> findByUserLoginAndPassword(String login, String password);

    Optional<Login> findByUser(User user);

    @Transactional
    void deleteByUser(User user);
}