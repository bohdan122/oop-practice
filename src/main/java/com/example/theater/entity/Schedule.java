package com.example.theater.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
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
}
