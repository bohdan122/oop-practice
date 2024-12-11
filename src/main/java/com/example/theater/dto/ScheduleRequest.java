package com.example.theater.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleRequest {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Min(value = 1, message = "Performance number must be at least 1")
    @Max(value = 100, message = "Performance number cannot exceed 100")
    private int performanceNumber;

    @NotNull(message = "Date is mandatory")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @NotBlank(message = "Director is mandatory")
    @Size(max = 50, message = "Director name cannot exceed 50 characters")
    private String director;

    @NotBlank(message = "Stage is mandatory")
    @Size(max = 50, message = "Stage name cannot exceed 50 characters")
    private String stage;
}
