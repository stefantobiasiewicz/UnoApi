package pl.polsl.UnoApi.Api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.Mqtt.MqttConfig;
import pl.polsl.UnoApi.Mqtt.MqttPublisher;
import pl.polsl.UnoApi.game.messages.JoinGameMessage;

@RestController
@AllArgsConstructor
public class MqttController {

    private MqttConfig mqttConfig;

    private MqttPublisher mqttPublisher;

    @GetMapping("/mqtt/addtopic")
    public void addTopic(){
        this.mqttConfig.addTopic("krzysio");
    }

    @DeleteMapping("/mqtt/addtopic")
    public void deleteTopic(){
        this.mqttConfig.removeTopic("krzysio");
    }

    @GetMapping("/mqtt/publish/{topic}")
    public void publish(@PathVariable(name = "topic") String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JoinGameMessage joinGameMessage = new JoinGameMessage();
        joinGameMessage.setUserId(1);
        mqttPublisher.publish(topic, objectMapper.writeValueAsString(joinGameMessage));
    }

}
