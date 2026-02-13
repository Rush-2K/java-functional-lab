package com.codingexercises.personaltaskmanager.service;

import com.codingexercises.personaltaskmanager.dto.TaskRequestDTO;
import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.entity.Prio;
import com.codingexercises.personaltaskmanager.entity.Task;
import com.codingexercises.personaltaskmanager.repository.TaskRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Task Service Test")
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Nested
    @DisplayName("Get All Task Test")
    class getAllTaskTest {

        @Test
        @DisplayName("Should get all task successfully")
        void shouldGetAllTaskSuccessfully() {

            //given
            Task task1 = Task.builder()
                    .id(2L)
                    .title("Task 1")
                    .completed(true)
                    .priority(Prio.MEDIUM)
                    .localDate(LocalDate.now())
                    .build();

            Task task2 = Task.builder()
                    .id(2L)
                    .title("Task 2")
                    .completed(false)
                    .priority(Prio.HIGH)
                    .localDate(LocalDate.now())
                    .build();

            when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

            //when
            List<TaskResponseDTO> result = taskService.getAllTask();

            //then
            //test result
            assertEquals(2, result.size());
            assertEquals("Task 1", result.get(0).getTitle());
            assertEquals("HIGH", result.get(1).getPriority());
            assertFalse(result.get(1).isStatus());
            //test invocation times
            verify(taskRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Create New Task Test")
    class createNewTaskTest {

        private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        @Test
        @DisplayName("should create new task successfully")
        void shouldCreateNewTaskSuccessfully() {
            // when
            TaskRequestDTO requestDTO = new TaskRequestDTO();
            requestDTO.setTitle("Task 1");
            requestDTO.setPriority(Prio.HIGH);

            Task savedTask = Task.builder()
                    .id(100L)
                    .title("Task 1")
                    .priority(Prio.HIGH)
                    .localDate(LocalDate.now())
                    .completed(false)
                    .build();

            when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

            //given
            TaskResponseDTO result = taskService.createNewTask(requestDTO);

            //then
            assertNotNull(result);
            assertEquals(100, result.getId());
            assertEquals("Task 1", result.getTitle());
            assertEquals("HIGH", result.getPriority());

            verify(taskRepository, times(1)).save(any(Task.class));
        }

        @Test
        @DisplayName("title should not be blank")
        void titleShouldNotBeBlank() {
            //when
            TaskRequestDTO requestDTO = new TaskRequestDTO();
            requestDTO.setTitle("");
            requestDTO.setPriority(Prio.HIGH);

            //given
            var violations = validator.validate(requestDTO);

            //then
            assertFalse(violations.isEmpty());
            assertEquals("Title cannot be blank", violations.iterator().next().getMessage());
        }

        @Test
        @DisplayName("title should not be null")
        void titleShouldNotBeNull() {
            //when
            TaskRequestDTO requestDTO = new TaskRequestDTO();
            requestDTO.setPriority(Prio.HIGH);

            //given
            var violations = validator.validate(requestDTO);

            //then
            assertFalse(violations.isEmpty());
            assertEquals("Title cannot be blank", violations.iterator().next().getMessage());
        }
    }
}
