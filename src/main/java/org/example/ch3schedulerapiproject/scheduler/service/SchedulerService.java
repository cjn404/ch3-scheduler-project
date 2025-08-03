package org.example.ch3schedulerapiproject.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerResponse;
import org.example.ch3schedulerapiproject.scheduler.dto.UpdateSchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.entity.Scheduler;
import org.example.ch3schedulerapiproject.scheduler.repository.SchedulerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    @Transactional
    public SchedulerResponse save(SchedulerRequest request) {
        Scheduler scheduler = new Scheduler(
                request.getPassword(),
                request.getName(),
                request.getTitle(),
                request.getContent()
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

    // '작성자명'이 있으면 '작성자명' 기준, 없으면 전체 목록
    @Transactional(readOnly = true)
    public List<SchedulerResponse> findSchedulers(String schedulerName) {
        List<Scheduler> schedulers;

        if (schedulerName != null && !schedulerName.isEmpty()) {
            schedulers = schedulerRepository.findByNameOrderByModifiedAtDesc(schedulerName);
        } else {
            schedulers = schedulerRepository.findAllByOrderByModifiedAtDesc();
        }

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
    public SchedulerResponse findScheduler(Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
//                () -> new IllegalArgumentException("해당 schedulerId" + schedulerId + "는 없습니다.") // 에러 코드 500
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 ID가 없습니다.") // 에러 코드 404
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

    @Transactional
    public SchedulerResponse updateScheduler(Long schedulerId, UpdateSchedulerRequest request) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당하는 ID가 없습니다.")
        );

        // 비밀번호 검증
        if (!scheduler.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }

        scheduler.updateScheduler(request.getName(), request.getTitle());
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
