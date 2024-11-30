package tech.lastbox.mappers;

import org.springframework.stereotype.Component;
import tech.lastbox.dtos.task.TaskCreateRequest;
import tech.lastbox.dtos.task.TaskDTO;
import tech.lastbox.dtos.task.TaskUpdateRequest;
import tech.lastbox.entities.Task;

import java.util.List;

@Component
public class TaskMapper {
    public Task toEntity(TaskUpdateRequest taskUpdateRequest) {
        return new Task(taskUpdateRequest.id(), taskUpdateRequest.status(), taskUpdateRequest.observation());
    }

    public Task toEntity(TaskCreateRequest taskCreateRequest) {
        return new Task(taskCreateRequest.name(), taskCreateRequest.description());
    }

    public TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getLastUpdateObservation(),
                task.getStatus().description(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public List<TaskDTO> toDTO(List<Task> tasks) {
        return tasks.stream()
                    .map(this::toDTO)
                    .toList();
    }
}
