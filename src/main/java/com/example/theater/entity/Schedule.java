package com.example.theater.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int performanceNumber;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String stage;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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
