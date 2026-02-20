package com.codingexercises.personaltaskmanager.dto;

import com.codingexercises.personaltaskmanager.entity.Prio;
import jakarta.validation.constraints.NotBlank;

public class UpdateStatusRequestDTO {
    private String title;
    private Prio priority;
    private boolean completed;
}
