package pl.polsl.UnoApi.game.messages;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StackChangedMessage extends Message{

    String firstCard;
    String secondCard;
    String thirdCard;
    String fourthCard;

    Double firstIndex;
    Double secondIndex;
    Double thirdIndex;
    Double fourthIndex;

    String cardOnTop;
}
