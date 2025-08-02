package org.example.ch3schedulerapiproject.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerResponse;
import org.example.ch3schedulerapiproject.scheduler.service.SchedulerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;

    @PostMapping("/schedulers")
    public ResponseEntity<SchedulerResponse> createScheduler(
            @RequestBody SchedulerRequest request
    ) {
        return ResponseEntity.ok(schedulerService.save(request));
    }

    // '작성자명'이 있으면 '작성자명' 기준, 없으면 전체 목록
    @GetMapping("/schedulers")
    public ResponseEntity<List<SchedulerResponse>> getSchedulers(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(schedulerService.findSchedulers(name));
    }

    @GetMapping("/schedulers/{schedulerId}")
    public ResponseEntity<SchedulerResponse> getScheduler(
            @PathVariable Long schedulerId
    ) {
        return ResponseEntity.ok(schedulerService.findScheduler(schedulerId));
    }
}
