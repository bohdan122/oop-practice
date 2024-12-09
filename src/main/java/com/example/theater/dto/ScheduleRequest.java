package com.example.theater.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleRequest {
    private String title;
    private int performanceNumber;
    private LocalDate date;
    private String director;
    private String stage;
}
