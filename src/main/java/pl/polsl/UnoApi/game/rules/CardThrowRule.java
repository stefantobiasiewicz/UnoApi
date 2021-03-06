package pl.polsl.UnoApi.game.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.messages.CardThrowMessage;
import pl.polsl.UnoApi.game.messages.PlayerMoveMessage;
import pl.polsl.UnoApi.game.rules.factory.Rule;
import pl.polsl.UnoApi.repository.GameRepository;

@Slf4j
public class CardThrowRule extends Rule<CardThrowMessage> {

    public CardThrowRule(MqttPublisher mqttPublisher, GameRepository gameRepository, ObjectMapper objectMapper) {
        super(CardThrowMessage.class,objectMapper,gameRepository,mqttPublisher);
    }

    @Override
    public void run(GamePlay gamePlay, CardThrowMessage message) {
        gamePlay.nextPlayer();
        PlayerMoveMessage nextPlayer = new PlayerMoveMessage();
        nextPlayer.setFrom(-1);
        nextPlayer.setUserId( gamePlay.getActiveGamePlayer().getUser().getId().intValue());
        try{
            mqttPublisher.publish(gamePlay.getGame().getTopic(), objectMapper.writeValueAsString(nextPlayer));
        }
        catch (JsonProcessingException ex){
            log.error("cannot convert GameStartMessage into string", ex);
        }
    }
}
