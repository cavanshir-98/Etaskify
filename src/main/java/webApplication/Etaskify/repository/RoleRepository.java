package webApplication.Etaskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApplication.Etaskify.enums.RoleEnum;
import webApplication.Etaskify.model.Role;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);




}
