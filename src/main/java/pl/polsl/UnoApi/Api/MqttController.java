package pl.polsl.UnoApi.Api;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.game.GameService;
import pl.polsl.UnoApi.game.config.MqttConfig;
import pl.polsl.UnoApi.game.config.MqttPublisher;

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
    public void publish(@PathVariable(name = "topic") String topic){
        mqttPublisher.publish(topic, "hehe");
    }

}
