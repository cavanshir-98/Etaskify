package webApplication.Etaskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webApplication.Etaskify.model.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
}
