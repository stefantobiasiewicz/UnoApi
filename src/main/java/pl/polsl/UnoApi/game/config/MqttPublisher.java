package pl.polsl.UnoApi.game.config;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
@Slf4j
public class MqttPublisher {

    private MqttConfig.MqttGateway gateway;

    public void publish(String topic, String data){
        log.info("message publish on: {} data: {}" , topic, data);
        gateway.senToMqtt(data,topic);
    }

}