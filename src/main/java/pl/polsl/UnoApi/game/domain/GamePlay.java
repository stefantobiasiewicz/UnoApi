package pl.polsl.UnoApi.game.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import pl.polsl.UnoApi.repository.dao.Game;
import pl.polsl.UnoApi.repository.dao.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@RequiredArgsConstructor
public class GamePlay {

    private Game game;

    private List<GamePlayer> queure = new ArrayList<>();
    private int activePlayer;

    private String topCard;

    public void nextPlayer(){
        activePlayer = ( activePlayer + 1 ) % queure.size();
    }

    public void reverse(){
        GamePlayer player = queure.get(activePlayer);
        Collections.reverse(queure);
        activePlayer = queure.indexOf(player);
    }

    public GamePlayer getActiveGamePlayer(){
        return queure.get(activePlayer);
    }

    public void cardThrow(){

    }

    public void colorChange(){

    }


    public void plusStack(){

    }

}
