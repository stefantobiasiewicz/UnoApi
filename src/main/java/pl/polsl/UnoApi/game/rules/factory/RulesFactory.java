package pl.polsl.UnoApi.game.rules.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.rules.CardThrowRule;
import pl.polsl.UnoApi.game.rules.JoinRule;
import pl.polsl.UnoApi.repository.GameRepository;

@Configuration
@AllArgsConstructor
public class RulesFactory {

    GameRepository gameRepository;
    MqttPublisher mqttPublisher;
    ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    JoinRule getJoinRule(){
        return new JoinRule(mqttPublisher, gameRepository, objectMapper);
    }

    @Bean
    CardThrowRule getCardThrowRule(){
        return new CardThrowRule(mqttPublisher, gameRepository, objectMapper);
    }

}
