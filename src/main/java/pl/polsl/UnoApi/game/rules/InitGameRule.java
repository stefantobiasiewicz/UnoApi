package pl.polsl.UnoApi.game.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.messages.InitGameMoveMessage;
import pl.polsl.UnoApi.game.messages.PlayerMoveMessage;
import pl.polsl.UnoApi.game.rules.factory.Rule;
import pl.polsl.UnoApi.repository.GameRepository;

@Slf4j
public class InitGameRule extends Rule<InitGameMoveMessage> {
    public InitGameRule(ObjectMapper objectMapper,
                        GameRepository gameRepository,
                        MqttPublisher mqttPublisher) {
        super(InitGameMoveMessage.class, objectMapper, gameRepository, mqttPublisher);
    }

    @Override
    public void run(GamePlay gamePlay, InitGameMoveMessage message) {
        gamePlay.nextPlayer();
        PlayerMoveMessage nextPlayer = new PlayerMoveMessage();
        nextPlayer.setFrom(-1);
        nextPlayer.setUserId(gamePlay.getActiveGamePlayer().getUser().getId().intValue());
        try{
            mqttPublisher.publish(gamePlay.getGame().getTopic(), objectMapper.writeValueAsString(nextPlayer));
        }
        catch (JsonProcessingException ex){
            log.error("cannot convert GameStartMessage into string", ex);
        }
    }
}
