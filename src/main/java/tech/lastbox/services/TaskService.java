package tech.lastbox.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.lastbox.dtos.task.TaskCreateRequest;
import tech.lastbox.dtos.task.TaskDTO;
import tech.lastbox.dtos.task.TaskUpdateRequest;
import tech.lastbox.entities.Task;
import tech.lastbox.entities.User;
import tech.lastbox.exceptions.NotFoundException;
import tech.lastbox.mappers.TaskMapper;
import tech.lastbox.repositories.TaskRepository;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public TaskDTO getTaskDTO(Task task) {
        return taskMapper.toDTO(task);
    }

    @Transactional
    public Task createTask(TaskCreateRequest taskCreateRequest) {
        return taskRepository.save(taskMapper.toEntity(taskCreateRequest));
    }

    @Transactional
    public Task updateTask(TaskUpdateRequest taskUpdateRequest, User user) throws NotFoundException {
        if (!taskRepository.existsByIdAndUser(taskUpdateRequest.id(), user)) throw new NotFoundException("Task não encontrada para o usuário.");

    }
}
