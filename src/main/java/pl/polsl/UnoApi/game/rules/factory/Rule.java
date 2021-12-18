package pl.polsl.UnoApi.game.rules.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.messages.Message;
import pl.polsl.UnoApi.repository.GameRepository;

@AllArgsConstructor
public abstract class Rule<T> {
    private final Class<T> messageType;
    protected ObjectMapper objectMapper;
    protected GameRepository gameRepository;
    protected MqttPublisher mqttPublisher;

    public boolean isApplicable(Message message){
        return messageType.isInstance(message);
    }


    public abstract void  run(GamePlay gamePlay, T message);

}
