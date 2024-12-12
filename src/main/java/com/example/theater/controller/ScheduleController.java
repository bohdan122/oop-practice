package com.example.theater.controller;

import com.example.theater.dto.ScheduleRequestDTO;
import com.example.theater.dto.ScheduleResponse;
import com.example.theater.entity.Schedule;
import com.example.theater.service.ScheduleService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Get schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleRequestDTO> getScheduleById(@PathVariable Long id) {
        return scheduleService.findById(id)
                .map(schedule -> ResponseEntity.ok(scheduleService.convertToDto(schedule))) // Now works with updated convertToDto method
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new schedule
    @PostMapping
    public ResponseEntity<ScheduleRequestDTO> createSchedule(@Valid @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = scheduleService.convertToEntity(scheduleRequestDTO);
        Schedule savedSchedule = scheduleService.save(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.convertToDto(savedSchedule));
    }
    

    // Update existing schedule
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleRequestDTO> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        Optional<Schedule> existingSchedule = scheduleService.findById(id);

        if (existingSchedule.isPresent()) {
            Schedule scheduleToUpdate = existingSchedule.get();
            // Map properties from ScheduleRequestDTO to Schedule entity
            scheduleToUpdate.setTitle(scheduleRequestDTO.getTitle());
            scheduleToUpdate.setDate(scheduleRequestDTO.getDate());
            scheduleToUpdate.setDirector(scheduleRequestDTO.getDirector());
            scheduleToUpdate.setStage(scheduleRequestDTO.getStage());
            scheduleToUpdate.setPerformanceNumber(scheduleRequestDTO.getPerformanceNumber());

            Schedule updatedSchedule = scheduleService.save(scheduleToUpdate);
            return ResponseEntity.ok(scheduleService.convertToDto(updatedSchedule));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete schedule by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        if (scheduleService.findById(id).isPresent()) {
            scheduleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
