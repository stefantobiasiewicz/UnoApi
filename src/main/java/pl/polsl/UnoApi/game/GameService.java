package pl.polsl.UnoApi.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.UnoApi.game.config.MqttConfig;
import pl.polsl.UnoApi.game.config.MqttPublisher;
import pl.polsl.UnoApi.repository.GameRepository;
import pl.polsl.UnoApi.repository.dao.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private MqttConfig mqttConfig;
    private MqttPublisher mqttPublisher;
    GameRepository gameRepository;

    List<Game> games = new ArrayList<>();

    public GameService(MqttConfig mqttConfig, MqttPublisher mqttPublisher, GameRepository gameRepository) {
        this.mqttConfig = mqttConfig;
        this.mqttPublisher = mqttPublisher;
        this.gameRepository = gameRepository;

        //List<Game> gamesStarted = this.gameRepository.findAllByGameStateIsBefore(GameState.End.toString());
        //this.games.addAll(gamesStarted);
    }

    public void createNewGame(int players){
        String gameTopic = UUID.randomUUID().toString();

        Game newGame = new Game();
        newGame.setTopic(gameTopic);
        newGame.setPlayers(players);
        newGame.setGameState(GameState.Wait);
        gameRepository.save(newGame);


    }
}
