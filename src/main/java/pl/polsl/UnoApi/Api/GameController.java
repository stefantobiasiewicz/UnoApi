package pl.polsl.UnoApi.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.genApi.GameApi;
import pl.polsl.UnoApi.service.GameService;
import pl.polsl.UnoApi.game.GameState;
import pl.polsl.UnoApi.mapper.GameMapper;
import pl.polsl.UnoApi.mapper.UserMapper;
import pl.polsl.UnoApi.model.CreateGameDto;
import pl.polsl.UnoApi.model.GameDto;
import pl.polsl.UnoApi.model.UserDto;
import pl.polsl.UnoApi.repository.GameRepository;
import pl.polsl.UnoApi.repository.UserRepository;
import pl.polsl.UnoApi.repository.dao.Game;
import pl.polsl.UnoApi.repository.dao.User;

import java.util.List;

@RestController
@AllArgsConstructor
public class GameController implements GameApi {

    GameMapper gameMapper;
    GameRepository gameRepository;
    GameService gameService;

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public ResponseEntity<GameDto> createNewGame(CreateGameDto createGameDto) {
        User mainPlayer = userRepository.getById(createGameDto.getMainPlayerId());
        List<User> players = userRepository.findAllById(createGameDto.getPlayers());

        return ResponseEntity.ok(
                gameMapper.gameToGameDto(gameService.createNewGame(mainPlayer,players))
        );
    }

    @Override
    public ResponseEntity<GameDto> getGame(Long id) {
        return ResponseEntity.ok(
                gameMapper.gameToGameDto(gameRepository.getById(id))
        );
    }

    @Override
    public ResponseEntity<List<UserDto>> getPlayers(Long id) {
        Game game = gameRepository.getById(id);
        return ResponseEntity.ok(
                userMapper.usersToUserDtos(gameService.getPlayersOfGame(game))
        );
    }

    @Override
    public ResponseEntity<List<GameDto>> getInvitation(Long userId) {
        User user = userRepository.getById(userId);
        return ResponseEntity.ok(
                gameMapper.gameListToGameDtoList(gameService.getGamesWhereUserAreInvted(user))
        );
    }

    @Override
    public ResponseEntity<List<GameDto>> getGameByStatus(String status) {
        var state =GameState.valueOf(status);
        var games = gameRepository.findAllByGameState(state);
        return ResponseEntity.ok(gameMapper.gameListToGameDtoList(games));
    }

    @Override
    public ResponseEntity<List<GameDto>> getGames() {
        var games = gameRepository.findAll();
        return ResponseEntity.ok(gameMapper.gameListToGameDtoList(games));
    }


}
