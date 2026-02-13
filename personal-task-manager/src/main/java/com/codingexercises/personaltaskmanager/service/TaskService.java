package com.codingexercises.personaltaskmanager.service;

import com.codingexercises.personaltaskmanager.dto.TaskRequestDTO;
import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.entity.Task;
import com.codingexercises.personaltaskmanager.mapper.TaskMapper;
import com.codingexercises.personaltaskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
}
