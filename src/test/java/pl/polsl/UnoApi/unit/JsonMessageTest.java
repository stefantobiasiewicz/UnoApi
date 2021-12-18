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
