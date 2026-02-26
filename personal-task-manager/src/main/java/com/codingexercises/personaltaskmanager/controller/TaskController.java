package com.codingexercises.personaltaskmanager.controller;

import com.codingexercises.personaltaskmanager.dto.TaskRequestDTO;
import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.dto.UpdateTaskRequestDTO;
import com.codingexercises.personaltaskmanager.entity.Prio;
import com.codingexercises.personaltaskmanager.entity.Task;
import com.codingexercises.personaltaskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id,
                                        @Valid @RequestBody UpdateTaskRequestDTO updateTaskRequestDTO) {
        taskService.updateTask(id, updateTaskRequestDTO);
        return ResponseEntity.ok("Update Task Successfully");
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Delete Task Successfully");
    }

    @GetMapping("/task/urgent")
    public ResponseEntity<?> getUrgentTask() {
        List<TaskResponseDTO> data = taskService.getUrgentTask();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/task/countTaskByPrio")
    public ResponseEntity<?> getTaskCountByPriority() {
        Map<Prio, Long> result =  taskService.getTaskCountByPriority();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/task/mostUrgent")
    public ResponseEntity<?> getMostUrgentTask() {
        Task data = taskService.getMostUrgentTask();
        return ResponseEntity.ok(data);
    }
}
