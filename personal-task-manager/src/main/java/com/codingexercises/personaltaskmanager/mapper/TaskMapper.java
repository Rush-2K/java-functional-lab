package com.codingexercises.personaltaskmanager.mapper;

import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.entity.Task;

public class TaskMapper {
    public static TaskResponseDTO toDto (Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setPriority(task.getPriority().toString());
        taskResponseDTO.setStatus(task.isCompleted());
        taskResponseDTO.setLocalDate(task.getLocalDate());

        return taskResponseDTO;
    }
}
