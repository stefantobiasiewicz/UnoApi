package pl.polsl.UnoApi.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.UnoApi.game.config.MqttConfig;
import pl.polsl.UnoApi.game.config.MqttPublisher;

@Service
@AllArgsConstructor
public class GameService {

    private MqttConfig mqttConfig;

    private MqttPublisher mqttPublisher;

    public void addTopic(){
        this.mqttConfig.addTopic("krzysio");
    }

    public void sednMsg() {
        mqttPublisher.publish("krzysio", "hehe");
    }
}
