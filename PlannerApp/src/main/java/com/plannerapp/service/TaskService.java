package com.plannerapp.service;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;

    public TaskService(UserRepository userRepository, CurrentUser currentUser, TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    public void addTask(AddTaskDTO addTaskDTO) {
        Task task = new Task();
        task.setDescription(addTaskDTO.getDescription());
        Priority priority = priorityRepository.findByName(addTaskDTO.getPriority());
        task.setPriority(priority);
        task.setDueDate(addTaskDTO.getDueDate());
        taskRepository.save(task);
    }

    public List<Task> getAllAvailableTasks() {
        return taskRepository.findByUserNull();
    }

    public List<Task> getMyTasks() {
        return taskRepository.findAllByUser_Id(currentUser.getId());
    }

    public void removeTaskById(long id) {
        taskRepository.deleteById(id);
    }

    public void returnTaskById(long id) {
        Task task = taskRepository.findById(id).get();
        task.setUser(null);
        taskRepository.save(task);
    }

    public void addTaskById(long id) {
        Task task = taskRepository.findById(id).get();
        User user = userRepository.findById(currentUser.getId()).get();
        task.setUser(user);
        taskRepository.save(task);
    }
}
