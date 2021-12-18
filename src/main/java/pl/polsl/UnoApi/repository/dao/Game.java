package pl.polsl.UnoApi.repository.dao;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.UnoApi.game.GameState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Game")
@Entity
@Getter
@Setter
public class Game {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "topic", unique = true)
    String topic;

    @Column(name = "players")
    Integer players;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_state")
    private GameState gameState;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Players> playerses = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    @JoinColumn(name = "main_player_id")
    private User mainPlayer;

}
