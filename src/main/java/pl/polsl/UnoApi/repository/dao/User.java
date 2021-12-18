package pl.polsl.UnoApi.repository.dao;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String login;

    private int winningCount;

    private int losesCount;

    @Nullable
    @Column(nullable = true)
    private String avatarUrl;
}
