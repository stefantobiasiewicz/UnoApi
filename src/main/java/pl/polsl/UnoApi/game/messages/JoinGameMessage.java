package pl.polsl.UnoApi.game.messages;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinGameMessage extends Message {

    private int userId;

}
