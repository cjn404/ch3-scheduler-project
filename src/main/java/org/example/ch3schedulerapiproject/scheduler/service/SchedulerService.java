package org.example.ch3schedulerapiproject.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerResponse;
import org.example.ch3schedulerapiproject.scheduler.entity.Scheduler;
import org.example.ch3schedulerapiproject.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Transactional
    public SchedulerResponse save(SchedulerRequest schedulerRequest) {
        Scheduler scheduler = new Scheduler(
                schedulerRequest.getPassword(),
                schedulerRequest.getName(),
                schedulerRequest.getTitle(),
                schedulerRequest.getContent()
        );
        Scheduler savedScheduler = schedulerRepository.save(scheduler);

        return new SchedulerResponse(
                savedScheduler.getId(),
                savedScheduler.getName(),
                savedScheduler.getTitle(),
                savedScheduler.getContent(),
                savedScheduler.getCreatedAt(),
                savedScheduler.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<SchedulerResponse> findSchedulers() {
        List<Scheduler> schedulers = schedulerRepository.findAll();
        List<SchedulerResponse> dtos = new ArrayList<>();

        for (Scheduler scheduler : schedulers) {
            SchedulerResponse schedulerResponse = new SchedulerResponse(
                    scheduler.getId(),
                    scheduler.getName(),
                    scheduler.getTitle(),
                    scheduler.getContent(),
                    scheduler.getCreatedAt(),
                    scheduler.getModifiedAt()
            );
            dtos.add(schedulerResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public SchedulerResponse findScheduler(Long id) {
        Scheduler scheduler = schedulerRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 " + id + "는 없습니다.")
        );
        return new SchedulerResponse(
                scheduler.getId(),
                scheduler.getName(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getCreatedAt(),
                scheduler.getModifiedAt()
        );
    }
}
