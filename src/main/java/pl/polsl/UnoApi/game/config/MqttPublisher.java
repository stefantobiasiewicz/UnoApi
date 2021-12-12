package pl.polsl.UnoApi.game.config;


import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MqttPublisher {

    private MqttGateway gateway;

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    private interface MqttGateway {
        void senToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
    }

    public void publish(String topic, String data){
        gateway.senToMqtt(data,topic);
    }

}