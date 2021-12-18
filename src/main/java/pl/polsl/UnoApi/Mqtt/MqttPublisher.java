package pl.polsl.UnoApi.Mqtt;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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