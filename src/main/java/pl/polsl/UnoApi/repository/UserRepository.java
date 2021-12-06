package pl.polsl.UnoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.UnoApi.repository.dao.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
