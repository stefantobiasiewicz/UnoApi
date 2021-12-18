package pl.polsl.UnoApi.game.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.GameState;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.domain.GamePlayer;
import pl.polsl.UnoApi.game.messages.GameStartMessage;
import pl.polsl.UnoApi.game.messages.JoinGameMessage;
import pl.polsl.UnoApi.game.rules.factory.Rule;
import pl.polsl.UnoApi.repository.GameRepository;

import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class JoinRule extends Rule<JoinGameMessage> {
    public JoinRule(MqttPublisher mqttPublisher, GameRepository gameRepository, ObjectMapper objectMapper) {
        super(JoinGameMessage.class, objectMapper, gameRepository, mqttPublisher);
    }

    @Override
    public void run(GamePlay gamePlay, JoinGameMessage message) {

        if (gamePlay.getGame().getGameState() != GameState.Wait)
            return;

        // dodanie nowego gracza
        var user = gamePlay.getGame()
                .getPlayerses()
                .stream()
                .filter(players -> players.getUser().getId() == message.getUserId())
                .collect(Collectors.toList());
        if(user.isEmpty()){
            log.warn("No such player in game, join message hacked {} ", message);
            return;
        }

        var userAllreadyInGame = gamePlay.getQueure()
                .stream()
                .filter(gamePlayer -> gamePlayer.equals(user.get(0)))
                .collect(Collectors.toList());
        if (!userAllreadyInGame.isEmpty()){
            log.warn("User {} is already in queue game", user);
            return;
        }

        GamePlayer player = new GamePlayer();
        player.setUser(user.get(0).getUser());
        player.setCardCount(5);

        gamePlay.getQueure().add(player);

        // gdy to ostatni gracz
        if (gamePlay.getQueure().size() == gamePlay.getGame().getPlayers()){
            Random rand = new Random();
            int randomPlayer = rand.nextInt(gamePlay.getQueure().size());

            gamePlay.setActivePlayer(randomPlayer);

            //todo randomowa karta na gore
            gamePlay.setTopCard("Y3");

            GameStartMessage gameStartMessage = new GameStartMessage();
            gameStartMessage.setFrom(-1);
            gameStartMessage.setCard(gamePlay.getTopCard());
            gameStartMessage.setUserId(gamePlay.getActivePlayer());
            try {
                gamePlay.getGame().setGameState(GameState.Started);
                gameRepository.save(gamePlay.getGame());

                mqttPublisher.publish(
                        gamePlay.getGame().getTopic(),
                        objectMapper.writeValueAsString(gameStartMessage)
                );
            }
            catch (JsonProcessingException ex){
                log.error("cannot convert GameStartMessage into string", ex);
            }
        }
    }
}
