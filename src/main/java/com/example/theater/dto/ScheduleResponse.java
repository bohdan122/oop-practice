package com.example.theater.dto;

import lombok.Data;

@Data
public class ScheduleResponse {
    private Long id;
    private String title;
    private int performanceNumber;
    private String date;
    private String director;
    private String stage;
}
