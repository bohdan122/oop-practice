package com.example.theater.service;

import com.example.theater.dto.ScheduleRequestDTO;
import com.example.theater.entity.Schedule;
import com.example.theater.repository.ScheduleRepository;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public ScheduleRequestDTO convertToDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleRequestDTO.class);
    }

    public Schedule convertToEntity(ScheduleRequestDTO scheduleRequestDTO) {
        return modelMapper.map(scheduleRequestDTO, Schedule.class);
    }

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
