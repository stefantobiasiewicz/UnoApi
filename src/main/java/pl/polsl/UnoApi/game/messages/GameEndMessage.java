package pl.polsl.UnoApi.game.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameEndMessage extends Message{

    public int winner;

}
