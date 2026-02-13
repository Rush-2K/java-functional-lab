package com.codingexercises.personaltaskmanager.dto;

import com.codingexercises.personaltaskmanager.entity.Prio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TaskRequestDTO {

    @Size(min = 2)
    @NotBlank
    private String title;

    @NotNull
    private Prio priority;
}
