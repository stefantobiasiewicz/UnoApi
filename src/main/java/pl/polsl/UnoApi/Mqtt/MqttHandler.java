package pl.polsl.UnoApi.Mqtt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import pl.polsl.UnoApi.service.GameService;

@Slf4j
@Configuration
@AllArgsConstructor
public class MqttHandler {

    GameService gameService;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            log.info("mqtt headers : {}\n message : {}",message.getHeaders() , message.getPayload());
            String topic = message.getHeaders().get("mqtt_receivedTopic", String.class);
            gameService.handler(topic, message.getPayload().toString());
        };
    }
}
