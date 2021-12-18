package pl.polsl.UnoApi.game.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerMoveMessage extends Message{
    int userId;
}
