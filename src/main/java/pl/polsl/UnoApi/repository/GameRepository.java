package pl.polsl.UnoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.UnoApi.game.GameState;
import pl.polsl.UnoApi.repository.dao.Game;
import pl.polsl.UnoApi.repository.dao.Players;
import pl.polsl.UnoApi.repository.dao.User;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameById(Long id);

    List<Game> findAllByGameState(GameState gameState);

    List<Game> findAllByPlayersesInAndGameStateEquals(List<Players> players, GameState state);
}
