package pl.polsl.UnoApi.game.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JoinGameMessage.class, name = "join"),
        @JsonSubTypes.Type(value = GameStartMessage.class, name = "GameStart"),
        @JsonSubTypes.Type(value = CardThrowMessage.class, name = "card"),
        @JsonSubTypes.Type(value = PlayerMoveMessage.class, name = "playerMove"),
        @JsonSubTypes.Type(value = GameEndMessage.class, name = "GameEnd"),
        @JsonSubTypes.Type(value = InitGameMoveMessage.class, name = "initialMove"),
        @JsonSubTypes.Type(value = StackChangedMessage.class, name = "stackChanged")
})
public abstract class Message implements Serializable {

    @Getter
    @Setter
    private int from;

}
