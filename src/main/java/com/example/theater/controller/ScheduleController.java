package com.example.theater.controller;

import com.example.theater.dto.ScheduleResponse;
import com.example.theater.dto.ScheduleRequest;
import com.example.theater.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String getSchedule(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                              @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                              Model model) {
        List<ScheduleResponse> schedule = scheduleService.getSchedule(startDate, endDate);
        model.addAttribute("schedule", schedule);
        return "schedule"; // Вказуємо на шаблон schedule.html
    }

    @GetMapping("/addSchedule")
    public String showAddScheduleForm(Model model) {
        model.addAttribute("scheduleRequest", new ScheduleRequest());
        return "addSchedule"; // Вказуємо на шаблон addSchedule.html
    }

    @PostMapping("/addSchedule")
    public String addSchedule(@ModelAttribute("scheduleRequest") ScheduleRequest scheduleRequest) {
        scheduleService.addSchedule(scheduleRequest);
        return "redirect:/schedule?startDate=2023-12-01&endDate=2023-12-31"; // Перенаправлення на графік
    }

    @GetMapping("/deleteSchedule")
    public String deleteSchedule(@RequestParam("id") Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedule?startDate=2023-12-01&endDate=2023-12-31";
    }
}
