package webApplication.Etaskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApplication.Etaskify.model.Organization;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);
}
