package com.example.theater.controller;

import com.example.theater.dto.ScheduleRequestDTO;
import com.example.theater.entity.Schedule;
import com.example.theater.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public String listSchedules(Model model) {
        List<Schedule> schedules = scheduleService.findAll();
        model.addAttribute("schedules", schedules);
        return "schedule/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("schedule", new ScheduleRequestDTO());
        return "schedule/create"; // This is the form view
    }
    
@PostMapping("/create")
public String createSchedule(@Valid @ModelAttribute("schedule") ScheduleRequestDTO scheduleRequestDTO,
                              BindingResult result, Model model) {
    if (result.hasErrors()) {
        result.getFieldErrors().forEach(error -> 
            System.out.println("Validation error: " + error.getField() + " - " + error.getDefaultMessage()));
        return "schedule/create"; // Return the form with validation errors
    }

    // Перевірка, чи коректно мапиться DTO у сутність
    Schedule schedule = scheduleService.convertToEntity(scheduleRequestDTO);
    System.out.println("Mapped Schedule Entity: " + schedule);

    // Спроба збереження у базу
    scheduleService.save(schedule);
    System.out.println("Schedule saved successfully!");

    return "redirect:/schedules"; // Повернення до списку розкладів
}


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Schedule> scheduleOptional = scheduleService.findById(id);
        if (scheduleOptional.isPresent()) {
            model.addAttribute("schedule", scheduleService.convertToDto(scheduleOptional.get()));
            return "schedule/edit";
        } else {
            return "redirect:/schedules";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateSchedule(@PathVariable Long id, @Valid @ModelAttribute("schedule") ScheduleRequestDTO scheduleRequestDTO,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "schedule/edit";
        }

        Optional<Schedule> existingSchedule = scheduleService.findById(id);
        if (existingSchedule.isPresent()) {
            Schedule scheduleToUpdate = existingSchedule.get();
            scheduleToUpdate.setTitle(scheduleRequestDTO.getTitle());
            scheduleToUpdate.setDate(scheduleRequestDTO.getDate());
            scheduleToUpdate.setDirector(scheduleRequestDTO.getDirector());
            scheduleToUpdate.setStage(scheduleRequestDTO.getStage());
            scheduleToUpdate.setPerformanceNumber(scheduleRequestDTO.getPerformanceNumber());

            scheduleService.save(scheduleToUpdate);
        }
        return "redirect:/schedules";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return "redirect:/schedules";
    }
}
