package pl.polsl.UnoApi.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.UnoApi.game.messages.*;
import pl.polsl.UnoApi.repository.dao.Game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


public class JsonMessageTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void joinMessageTest() throws JsonProcessingException {

        JoinGameMessage joinGameMessage = new JoinGameMessage();
        joinGameMessage.setUserId(10);
        String json = objectMapper.writeValueAsString(joinGameMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"join\",\"from\":0,\"userId\":10}",json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"join\",\"from\":0,\"userId\":5}", Message.class);

        assertTrue(testJoinMessage instanceof JoinGameMessage);
    }

    @Test
    void cardMessageTest() throws JsonProcessingException {

        CardThrowMessage cardThrowMessage = new CardThrowMessage();
        cardThrowMessage.setCard("Y2");
        String json = objectMapper.writeValueAsString(cardThrowMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"card\",\"from\":0,\"card\":\"Y2\"}",json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"card\",\"from\":0,\"card\":\"Y2\"}", Message.class);

        assertTrue(testJoinMessage instanceof CardThrowMessage);
    }

    @Test
    void playerMoveMessageTest() throws JsonProcessingException {

        PlayerMoveMessage playerMoveMessage = new PlayerMoveMessage();
        playerMoveMessage.setUserId(2);
        String json = objectMapper.writeValueAsString(playerMoveMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"playerMove\",\"from\":0,\"userId\":2}", json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"playerMove\",\"from\":0,\"userId\":2}", Message.class);

        assertTrue(testJoinMessage instanceof PlayerMoveMessage);
    }

    @Test
    void gameStartMessageTest() throws JsonProcessingException {

        GameStartMessage gameStartMessage = new GameStartMessage();
        gameStartMessage.setFrom(-1);
        gameStartMessage.setCard("R2");
        gameStartMessage.setUserId(1);

        String json = objectMapper.writeValueAsString(gameStartMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"GameStart\",\"from\":-1,\"card\":\"R2\",\"userId\":1}", json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"GameStart\",\"from\":-1,\"card\":\"R2\",\"userId\":1}", Message.class);

        assertTrue(testJoinMessage instanceof GameStartMessage);
    }

    @Test
    void gameEndMessageTest() throws JsonProcessingException {

        GameEndMessage gameEndMessage = new GameEndMessage();
        gameEndMessage.setWinner(4);
        String json = objectMapper.writeValueAsString(gameEndMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"GameEnd\",\"from\":0,\"winner\":4}", json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"GameEnd\",\"from\":0,\"winner\":2}", Message.class);

        assertTrue(testJoinMessage instanceof GameEndMessage);
    }

    @Test
    void initGameMoveMessageTest() throws JsonProcessingException {

        InitGameMoveMessage initGameMoveMessage = new InitGameMoveMessage();
        initGameMoveMessage.setCard1("Y1");
        initGameMoveMessage.setCard2("Y2");
        initGameMoveMessage.setCard3("Y3");
        initGameMoveMessage.setCard4("Y4");
        initGameMoveMessage.setCard5("Y5");
        String json = objectMapper.writeValueAsString(initGameMoveMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"initialMove\",\"from\":0,\"card1\":\"Y1\",\"card2\":\"Y2\",\"card3\":\"Y3\",\"card4\":\"Y4\",\"card5\":\"Y5\"}", json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"initialMove\",\"from\":0,\"card1\":\"Y1\",\"card2\":\"Y2\",\"card3\":\"Y3\",\"card4\":\"Y4\",\"card5\":\"Y5\"}", Message.class);

        assertTrue(testJoinMessage instanceof InitGameMoveMessage);
    }

    @Test
    void stackChangedMessageTest() throws JsonProcessingException {

        StackChangedMessage stackChangedMessage = new StackChangedMessage();
        stackChangedMessage.setFirstCard("redTwo");
        stackChangedMessage.setSecondCard("redTwo");
        stackChangedMessage.setThirdCard("redTwo");
        stackChangedMessage.setFourthCard("redTwo");
        stackChangedMessage.setFirstIndex(1.0);
        stackChangedMessage.setSecondIndex(2.0);
        stackChangedMessage.setThirdIndex(3.0);
        stackChangedMessage.setFourthIndex(4.0);
        stackChangedMessage.setCardOnTop("redTwo");
        String json = objectMapper.writeValueAsString(stackChangedMessage);

        System.out.println(json);

        assertEquals("{\"type\":\"stackChanged\",\"from\":0,\"firstCard\":\"redTwo\",\"secondCard\":\"redTwo\",\"thirdCard\":\"redTwo\",\"fourthCard\":\"redTwo\",\"firstIndex\":1.0,\"secondIndex\":2.0,\"thirdIndex\":3.0,\"fourthIndex\":4.0,\"cardOnTop\":\"redTwo\"}", json);

        Message testJoinMessage = objectMapper.readValue("{\"type\":\"stackChanged\",\"from\":0,\"firstCard\":\"redTwo\",\"secondCard\":\"redTwo\",\"thirdCard\":\"redTwo\",\"fourthCard\":\"redTwo\",\"firstIndex\":1.0,\"secondIndex\":2.0,\"thirdIndex\":3.0,\"fourthIndex\":4.0,\"cardOnTop\":\"redTwo\"}", Message.class);

        assertTrue(testJoinMessage instanceof StackChangedMessage);
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "Messages/joinGameMessage.json",
            "Messages/cardThrowMessage.json",
            "Messages/playerMoveMessage.json",
            "Messages/gameStartMessage.json",
            "Messages/gameEndMessage.json"
    })
    void jsonMessagesTest(String path) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        assertTrue(absolutePath.endsWith("/"+ path));

        String json = Files.readString(Path.of(absolutePath));

        Message message = objectMapper.readValue(json, Message.class);


    }

}
