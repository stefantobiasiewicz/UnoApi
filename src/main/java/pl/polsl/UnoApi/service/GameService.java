package pl.polsl.UnoApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.polsl.UnoApi.game.GameEngine;
import pl.polsl.UnoApi.game.GameState;
import pl.polsl.UnoApi.Mqtt.MqttConfig;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.messages.Message;
import pl.polsl.UnoApi.repository.GameRepository;
import pl.polsl.UnoApi.repository.dao.Game;
import pl.polsl.UnoApi.repository.dao.Players;
import pl.polsl.UnoApi.repository.PlayersRepository;
import pl.polsl.UnoApi.repository.dao.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Service
public class GameService {

    private MqttConfig mqttConfig;
    private MqttPublisher mqttPublisher;
    GameRepository gameRepository;
    PlayersRepository playersRepository;

    GameEngine gameEngine;

    ObjectMapper objectMapper = new ObjectMapper();


    public GameService(MqttConfig mqttConfig,
                       MqttPublisher mqttPublisher,
                       GameRepository gameRepository,
                       PlayersRepository playersRepository,
                       GameEngine gameEngine) {
        this.mqttConfig = mqttConfig;
        this.mqttPublisher = mqttPublisher;
        this.gameRepository = gameRepository;
        this.playersRepository = playersRepository;
        this.gameEngine = gameEngine;


        this.gameRepository.findAll().forEach(
                game -> {
                    if(game.getGameState() != GameState.End){
                        game.setGameState(GameState.End);
                        gameRepository.save(game);
                    }
                }
        );
    }

    public Game createNewGame(User mainPlayer, List<User> players){
        String gameTopic = mqttConfig.getPrefix() + "/" + UUID.randomUUID().toString();

        Game newGame = new Game();
        newGame.setTopic(gameTopic);
        newGame.setPlayers(players.size());
        newGame.setMainPlayer(mainPlayer);
        newGame.setGameState(GameState.Wait);

        List<Players> gamePlayers = new ArrayList<>();
        players.forEach(user -> {
            Players player = new Players();
            player.setUser(user);
            player.setGame(newGame);
            gamePlayers.add(player);
        });
        newGame.setPlayerses(gamePlayers);
        Game createdGame = gameRepository.save(newGame);
        gameEngine.createNewGame(createdGame);

        mqttConfig.addTopic(createdGame.getTopic());

        return createdGame;
    }

    public List<User> getPlayersOfGame(Game game){
        List<User> players = new ArrayList<>();
        game.getPlayerses().forEach(player -> players.add(player.getUser()));
        return players;
    }

    public List<Game> getGamesWhereUserAreInvted(User user){
        List<Players> player = playersRepository.findAllByUser(user);
        return gameRepository.findAllByPlayersesInAndGameStateEquals(player, GameState.Wait);
    }


    public void handler(String topic, String payload) {
        if(!topic.startsWith(mqttConfig.getPrefix())){
            log.warn("Message arrive without prefix, topic {}", topic);
            return;
        }

        Game game = gameEngine.getGames().stream()
                .filter(gameInMemory -> Objects.equals(gameInMemory.getTopic(), topic))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> {
                            if (list.size() != 1) {
                                log.warn("In mempory are more than one game with " +
                                        "the sane topic {}", topic);
                                throw new IllegalStateException("In mempory are more than one game with " +
                                        "the sane topic");
                            }
                            return list.get(0);
                        }));

        try{
            Message message = objectMapper.readValue(payload, Message.class);
            if(message.getFrom() != -1)
                gameEngine.gameManage(game, message);
        }
        catch (JsonProcessingException ex){
            log.warn("Cannot parse message {}", payload);
        }
    }

}
