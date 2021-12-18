package pl.polsl.UnoApi.unit;

import org.junit.jupiter.api.Test;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.domain.GamePlayer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEngineTest {

    @Test
    void nextPlayerTest(){
        GamePlay gamePlay = new GamePlay();

        List<GamePlayer> queure = new ArrayList<>();
        queure.add(new GamePlayer());
        queure.add(new GamePlayer());
        queure.add(new GamePlayer());
        queure.add(new GamePlayer());
        gamePlay.setQueure(queure);

        gamePlay.setActivePlayer(0);

        assertEquals(0,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(1,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(2,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(3,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(0,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(1,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(2,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(3,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(0,gamePlay.getActivePlayer());
    }

    @Test
    void reverseMethodTest(){
        GamePlay gamePlay = new GamePlay();

        List<GamePlayer> queure = new ArrayList<>();
        GamePlayer p1 = new GamePlayer();
        GamePlayer p2 = new GamePlayer();
        GamePlayer p3 = new GamePlayer();
        GamePlayer p4 = new GamePlayer();
        queure.add(p1);
        queure.add(p2);
        queure.add(p3);
        queure.add(p4);
        gamePlay.setQueure(queure);
        gamePlay.setActivePlayer(0);

        assertEquals(0,gamePlay.getActivePlayer());
        gamePlay.reverse();
        assertEquals(3,gamePlay.getActivePlayer());
        gamePlay.nextPlayer();
        assertEquals(p4, gamePlay.getActiveGamePlayer());

        gamePlay.nextPlayer();
        gamePlay.nextPlayer();
        assertEquals(p2, gamePlay.getActiveGamePlayer());
        gamePlay.reverse();
        gamePlay.nextPlayer();
        assertEquals(p3, gamePlay.getActiveGamePlayer());

        gamePlay.reverse();
        gamePlay.nextPlayer();
        assertEquals(p2, gamePlay.getActiveGamePlayer());
    }

}
