package com.example.theater.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ScheduleResponse {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    private Integer performanceNumber;

    @NotNull
    @Size(min = 10, max = 10) // Assuming the date is in the format "yyyy-MM-dd"
    private String date;

    @NotNull
    @Size(min = 1, max = 255)
    private String director;

    @NotNull
    @Size(min = 1, max = 255)
    private String stage;
}
