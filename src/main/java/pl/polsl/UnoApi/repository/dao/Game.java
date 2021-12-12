package pl.polsl.UnoApi.repository.dao;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.UnoApi.game.GameState;

import javax.persistence.*;

@Table(name = "Game")
@Entity
@Getter
@Setter
public class Game {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "topic")
    String topic;

    @Column(name = "players")
    Integer players;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_state")
    private GameState gameState;

}
