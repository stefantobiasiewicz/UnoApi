package pl.polsl.UnoApi.game.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.messages.CardThrowMessage;
import pl.polsl.UnoApi.game.rules.factory.Rule;
import pl.polsl.UnoApi.repository.GameRepository;

public class CardThrowRule extends Rule<CardThrowMessage> {

    public CardThrowRule(MqttPublisher mqttPublisher, GameRepository gameRepository, ObjectMapper objectMapper) {
        super(CardThrowMessage.class,objectMapper,gameRepository,mqttPublisher);
    }

    @Override
    public void run(GamePlay gamePlay, CardThrowMessage message) {



    }
}
