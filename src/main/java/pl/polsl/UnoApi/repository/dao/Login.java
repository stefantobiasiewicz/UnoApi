package pl.polsl.UnoApi.repository.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "login", referencedColumnName = "login")
    private User user;

}
