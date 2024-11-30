package tech.lastbox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lastbox.entities.Task;
import tech.lastbox.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByIdAndUser(long id, User user);
}
