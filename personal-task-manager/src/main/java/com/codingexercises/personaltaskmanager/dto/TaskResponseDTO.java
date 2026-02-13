package com.codingexercises.personaltaskmanager.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
public class TaskResponseDTO {
    private Long id;
    private String title;
    private boolean status;
    private String priority;
    private LocalDate localDate;
}
