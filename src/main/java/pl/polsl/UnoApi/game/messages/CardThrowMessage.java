package pl.polsl.UnoApi.game.messages;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardThrowMessage extends Message{

    private String card;

}
