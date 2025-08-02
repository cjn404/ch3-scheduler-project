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

    @GetMapping("/schedulers")
    public ResponseEntity<List<SchedulerResponse>> getSchedulers() {
        return ResponseEntity.ok(schedulerService.findSchedulers());
    }

    @GetMapping("/schedulers/{schedulerId}")
    public ResponseEntity<SchedulerResponse> getScheduler(
            @PathVariable Long schedulerId
    ) {
        return ResponseEntity.ok(schedulerService.findScheduler(schedulerId));
    }
}
