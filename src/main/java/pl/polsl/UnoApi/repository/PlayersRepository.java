package pl.polsl.UnoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.UnoApi.repository.dao.Players;
import pl.polsl.UnoApi.repository.dao.User;

import java.util.List;

public interface PlayersRepository extends JpaRepository<Players, Long> {

    List<Players> findAllByUser(User user);
}