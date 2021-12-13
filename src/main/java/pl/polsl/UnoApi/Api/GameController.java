package pl.polsl.UnoApi.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.polsl.UnoApi.api.GameApi;
import pl.polsl.UnoApi.game.GameService;
import pl.polsl.UnoApi.mapper.GameMapper;
import pl.polsl.UnoApi.model.GameDto;
import pl.polsl.UnoApi.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class GameController implements GameApi {

    GameMapper gameMapper;
    GameRepository gameRepository;
    GameService gameService;

    @Override
    public ResponseEntity<Void> createNewGame(Integer players) {
        gameService.createNewGame(players);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<GameDto> getGame(Integer id) {
        var ret = gameMapper.gameToGameDto(gameRepository.findGameById(id.longValue()));
        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<List<GameDto>> getGameByStatus(String status) {
        return GameApi.super.getGameByStatus(status);
    }

    @Override
    public ResponseEntity<List<GameDto>> getGames() {
        return GameApi.super.getGames();
    }

    @Override
    public ResponseEntity<List<GameDto>> getUserGames(Integer userId) {
        return GameApi.super.getUserGames(userId);
    }

    @Override
    public ResponseEntity<List<GameDto>> getUserGamesByStatus(Integer userId, String status) {
        return GameApi.super.getUserGamesByStatus(userId, status);
    }
}
