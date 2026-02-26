package com.codingexercises.personaltaskmanager.dto;

import com.codingexercises.personaltaskmanager.entity.Prio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateTaskRequestDTO {
    @Size(min = 3, max = 50)
    private String title;
    private Prio priority;
    private Boolean completed;
    private LocalDate dueDate;
}
