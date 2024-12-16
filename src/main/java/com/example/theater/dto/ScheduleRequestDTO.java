package com.example.theater.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ScheduleRequestDTO {


    @NotNull(message = "Title is mandatory")
    @Size(min=3, max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Min(value = 1, message = "Performance number must be at least 1")
    @Max(value = 100, message = "Performance number cannot exceed 100")
    private int performanceNumber;

    @NotNull(message = "Date is mandatory")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @NotNull(message = "Director is mandatory")
    @Size(max = 50, message = "Director name cannot exceed 50 characters")
    private String director;

    @NotNull(message = "Stage is mandatory")
    @Size(max = 50, message = "Stage name cannot exceed 50 characters")
    private String stage;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPerformanceNumber() {
        return performanceNumber;
    }
    public void setPerformanceNumber(int performanceNumber) {
        this.performanceNumber = performanceNumber;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
}
