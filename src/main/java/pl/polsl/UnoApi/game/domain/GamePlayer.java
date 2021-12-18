package pl.polsl.UnoApi.game.domain;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.UnoApi.repository.dao.User;

@Getter
@Setter
public class GamePlayer {

    private User user;

    private int cardCount;


}
