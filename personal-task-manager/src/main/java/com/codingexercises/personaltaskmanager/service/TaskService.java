package com.codingexercises.personaltaskmanager.service;

import com.codingexercises.personaltaskmanager.dto.TaskRequestDTO;
import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.dto.UpdateTaskRequestDTO;
import com.codingexercises.personaltaskmanager.entity.Prio;
import com.codingexercises.personaltaskmanager.entity.Task;
import com.codingexercises.personaltaskmanager.mapper.TaskMapper;
import com.codingexercises.personaltaskmanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponseDTO> getAllTask() {
        List<Task> task = taskRepository.findAll();

        List<TaskResponseDTO> data = task.stream()
                .map(t -> TaskMapper.toDto(t))
                .collect(Collectors.toList());

        return data;
    }

    public TaskResponseDTO createNewTask(TaskRequestDTO requestDTO) {
        Task task = new Task();
        task.setTitle(requestDTO.getTitle());
        task.setPriority(requestDTO.getPriority());
        task.setLocalDate(LocalDate.now());
        task.setCompleted(false);

        Task savedTask = taskRepository.save(task);

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(savedTask.getId());
        taskResponseDTO.setTitle(savedTask.getTitle());
        taskResponseDTO.setPriority(savedTask.getPriority().toString());
        taskResponseDTO.setStatus(savedTask.isCompleted());
        taskResponseDTO.setLocalDate(savedTask.getLocalDate());

        return taskResponseDTO;
    }

    public void updateTask(Long taskId, UpdateTaskRequestDTO updateTaskRequestDTO) {

        // get task object by id
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task cannot be found"));

        // validate each field
        if (updateTaskRequestDTO.getTitle() != null) {
            task.setTitle(updateTaskRequestDTO.getTitle());
        }
        if (updateTaskRequestDTO.getPriority() != null) {
            task.setPriority(updateTaskRequestDTO.getPriority());
        }
        if (updateTaskRequestDTO.getCompleted() != null) {
            task.setCompleted(updateTaskRequestDTO.getCompleted());
        }
        if (updateTaskRequestDTO.getDueDate() != null) {
            task.setLocalDate(updateTaskRequestDTO.getDueDate());
        }

        taskRepository.save(task);

    }

    public void deleteTask(Long taskId) {
        Task taskToBeDelete = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task Not Found"));
        taskRepository.delete(taskToBeDelete);
    }

    public List<TaskResponseDTO> getUrgentTask() {
        List<Task> data = taskRepository.findAll();
        List<TaskResponseDTO> list = data.stream()
                .filter(d -> d.getPriority() == Prio.HIGH)
                .map(d -> TaskMapper.toDto(d))
                .toList();

        return list;
    }

    public Map<Prio, Long> getTaskCountByPriority() {
        List<Task> data = taskRepository.findAll();
        Map<Prio, Long> collect = data.stream()
                .collect(Collectors.groupingBy(d -> d.getPriority(),
                        Collectors.counting()));

        return collect;
    }

    public Task getMostUrgentTask() {
        List<Task> data = taskRepository.findAll();
        Task task = data.stream()
                .min(Comparator.comparing(d -> d.getLocalDate()))
                .orElse(null);

        return task;
    }
}
