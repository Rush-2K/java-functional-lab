package com.codingexercises.personaltaskmanager.dto;

import com.codingexercises.personaltaskmanager.entity.Prio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull
    private Prio priority;
}
