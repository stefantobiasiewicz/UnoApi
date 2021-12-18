package pl.polsl.UnoApi.game.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStartMessage extends Message{

    private String card;
    private int userId;

}
