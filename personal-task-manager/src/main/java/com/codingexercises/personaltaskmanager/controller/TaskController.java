package com.codingexercises.personaltaskmanager.controller;

import com.codingexercises.personaltaskmanager.dto.TaskRequestDTO;
import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.entity.Task;
import com.codingexercises.personaltaskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public ResponseEntity<?> getAllTask() {
        List<TaskResponseDTO> data = taskService.getAllTask();

        return ResponseEntity.ok().body(data);
    }

    @PostMapping("/task")
    public ResponseEntity<?> createNewTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        TaskResponseDTO response = taskService.createNewTask(taskRequestDTO);

        return ResponseEntity.ok(response);
    }
}
