package pl.polsl.UnoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.UnoApi.repository.dao.Game;

public interface GameRepository extends JpaRepository<Game, Long> {


}
