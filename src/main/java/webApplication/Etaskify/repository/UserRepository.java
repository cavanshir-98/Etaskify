package webApplication.Etaskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApplication.Etaskify.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
