package pl.polsl.UnoApi.game;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.polsl.UnoApi.game.domain.GamePlay;
import pl.polsl.UnoApi.game.domain.GamePlayer;
import pl.polsl.UnoApi.game.messages.Message;
import pl.polsl.UnoApi.game.rules.factory.Rule;
import pl.polsl.UnoApi.repository.dao.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GameEngine {


    private List<Rule> rules;

    private List<GamePlay> games = new ArrayList<>();

    public GameEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public void gameManage(Game game, Message message){

        Rule rule = getRuleByMessage(message);
        if(rule == null)    return;
        rule.run(getGamePlayByGame(game), message);

    }

    private Rule getRuleByMessage(Message message) {
        var rules = this.rules.stream()
                .filter(rule -> rule.isApplicable(message))
                .collect(Collectors.toList());
        if (rules.isEmpty()) {
            log.warn("No such game Rule");
            return null;
        }
        if (rules.size() > 1) {
            log.warn("There is more than 1 game rule mach to this message");
        }
        return rules.get(0);
    }

    private GamePlay getGamePlayByGame(Game game){
        return games.stream()
                .filter(gamePlay -> gamePlay.getGame().equals(game))
                .collect(Collectors.toList())
                .get(0);
    }


    public List<Game> getGames(){
        List<Game> allGames = new ArrayList<>();
        games.stream()
                .forEach(gamePlay -> allGames.add(gamePlay.getGame()));
        return  allGames;
    }

    public void createNewGame(Game game){
        GamePlay createdGame = new GamePlay();
        createdGame.setGame(game);
        games.add(createdGame);
    }
}
