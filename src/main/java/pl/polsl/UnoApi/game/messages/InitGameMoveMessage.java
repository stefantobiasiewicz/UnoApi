package pl.polsl.UnoApi.game.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitGameMoveMessage extends Message{

    private String card1;
    private String card2;
    private String card3;
    private String card4;
    private String card5;

}
