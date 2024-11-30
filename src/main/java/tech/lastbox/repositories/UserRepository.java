package tech.lastbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lastbox.entities.User;
import tech.lastbox.lastshield.security.core.annotations.UserHandler;

import java.util.Optional;

@UserHandler
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    Optional<User> findUserByUsername(String username);
}
