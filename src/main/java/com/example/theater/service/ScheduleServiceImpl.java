package com.example.theater.service;

import com.example.theater.dto.ScheduleRequest;
import com.example.theater.dto.ScheduleResponse;
import com.example.theater.entity.Schedule;
import com.example.theater.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.setTitle(request.getTitle());
        schedule.setPerformanceNumber(request.getPerformanceNumber());
        schedule.setDate(request.getDate());
        schedule.setDirector(request.getDirector());
        schedule.setStage(request.getStage());

        Schedule saved = scheduleRepository.save(schedule);

        return mapToResponse(saved);
    }

    @Override
    public List<ScheduleResponse> getSchedule(LocalDate startDate, LocalDate endDate) {
        List<Schedule> schedules = scheduleRepository.findByDateBetween(startDate, endDate);
        return schedules.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ScheduleResponse mapToResponse(Schedule schedule) {
        ScheduleResponse response = new ScheduleResponse();
        response.setId(schedule.getId());
        response.setTitle(schedule.getTitle());
        response.setPerformanceNumber(schedule.getPerformanceNumber());
        response.setDate(schedule.getDate().toString());
        response.setDirector(schedule.getDirector());
        response.setStage(schedule.getStage());
        return response;
    }
}
