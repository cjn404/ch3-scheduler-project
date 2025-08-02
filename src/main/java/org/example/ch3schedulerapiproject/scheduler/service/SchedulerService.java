package org.example.ch3schedulerapiproject.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerResponse;
import org.example.ch3schedulerapiproject.scheduler.entity.Scheduler;
import org.example.ch3schedulerapiproject.scheduler.repository.SchedulerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
