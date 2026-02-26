package com.codingexercises.personaltaskmanager.service;

import com.codingexercises.personaltaskmanager.dto.TaskRequestDTO;
import com.codingexercises.personaltaskmanager.dto.TaskResponseDTO;
import com.codingexercises.personaltaskmanager.dto.UpdateTaskRequestDTO;
import com.codingexercises.personaltaskmanager.entity.Prio;
import com.codingexercises.personaltaskmanager.entity.Task;
import com.codingexercises.personaltaskmanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;

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

    @Nested
    @DisplayName("Update Task Test")
    class updateTaskTest {

        @Test
        @DisplayName("Should Update Task Successfully")
        void shouldUpdateTaskSuccessfully() {
            // 1. arrange
            Long taskId = 1L;

            // Existing task in the DB
            Task existingTask = Task.builder()
                    .title("Old Title")
                    .priority(Prio.LOW)
                    .build();

            // The request - we only want to change the title, the rest are null
            UpdateTaskRequestDTO updateTaskRequestDTO = new UpdateTaskRequestDTO();
            updateTaskRequestDTO.setTitle("New Title");

            when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));

            // 2. act
            taskService.updateTask(taskId, updateTaskRequestDTO);

            // 3. assert
            assertEquals("New Title", existingTask.getTitle());
            assertEquals(Prio.LOW, existingTask.getPriority());

            verify(taskRepository, times(1)).save(any(Task.class));
        }

        @Test
        @DisplayName("Should Throw Exception")
        void shouldThrowExceptionWhenTaskNotFound() {
            // ARRANGE
            Long taskId = 1L;
            UpdateTaskRequestDTO request = new UpdateTaskRequestDTO();

            // mock repo to return empty
            when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

            // ACT & ASSERT
            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> taskService.updateTask(taskId, request));

            assertEquals("Task cannot be found", exception.getMessage());

            verify(taskRepository, never()).save(any(Task.class));
        }
    }

    @Nested
    @DisplayName("Delete Task Test")
    class deleteTaskTest {

        @Test
        @DisplayName("should delete task successfuly")
        void shouldDeleteTaskSuccessfully() {
            // arrange
            Long taskId = 1L;

            // Existing task in the DB
            Task existingTask = Task.builder()
                    .title("Old Title")
                    .priority(Prio.LOW)
                    .build();

            when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));

            // act
            taskService.deleteTask(taskId);

            // assert
            verify(taskRepository, times(1)).delete(any(Task.class));

        }

        @Test
        @DisplayName("should throw exception when deleting non-existent task")
        void shouldThrowExceptionWhenTaskNotFound() {
            // arrange
            Long taskId = 99L;

            when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

            // act
            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> taskService.deleteTask(taskId)
            );

            // assert
            assertEquals("Task Not Found", exception.getMessage());

            verify(taskRepository, never()).delete(any(Task.class));
        }
    }
}
