package com.example.theater.service;

import com.example.theater.dto.ScheduleRequest;
import com.example.theater.dto.ScheduleResponse;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponse addSchedule(ScheduleRequest request);
    void deleteSchedule(Long id);
    List<ScheduleResponse> getSchedule(LocalDate startDate, LocalDate endDate);
}
