package tech.lastbox.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.lastbox.dtos.task.TaskCreateRequest;
import tech.lastbox.dtos.task.TaskDTO;
import tech.lastbox.entities.Task;
import tech.lastbox.entities.User;
import tech.lastbox.exceptions.user.AlreadyExistsException;
import tech.lastbox.exceptions.user.InvalidDataException;
import tech.lastbox.repositories.UserRepository;
import static tech.lastbox.util.UserDataValidation.*;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TaskService taskService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TaskService taskService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskService = taskService;
    }

    public boolean authenticateUser(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findUserByUsername(username);
        return userOpt.filter(user -> passwordEncoder.matches(rawPassword, user.getPassword())).isPresent();
    }

    @Transactional
    public User registerUser(String username, String rawPassword) throws AlreadyExistsException, InvalidDataException {
        if (userRepository.existsByUsername(username)) throw new AlreadyExistsException("Já existe um usuário com este usuário.");

        if (!isValidUsername(username)) throw new InvalidDataException("O usuário informado é inválido.");
        if (!isValidPassword(rawPassword)) throw new InvalidDataException("A senha informada é inválida (mínimo 8 caracteres).");

        return userRepository.saveAndFlush(new User(username, passwordEncoder.encode(rawPassword)));
    }

    @Transactional
    public TaskDTO addTaskToUser(TaskCreateRequest taskCreateRequest, User user) {
        Task task = taskService.createTask(taskCreateRequest);
        userRepository.save(user.addTask(task));
        return taskService.getTaskDTO(task);
    }

}
