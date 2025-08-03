package org.example.ch3schedulerapiproject.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ch3schedulerapiproject.scheduler.dto.DeleteSchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerRequest;
import org.example.ch3schedulerapiproject.scheduler.dto.SchedulerResponse;
import org.example.ch3schedulerapiproject.scheduler.dto.UpdateSchedulerRequest;
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
            @Valid @RequestBody SchedulerRequest request // @NotBlank 세트
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

    @PatchMapping("/schedulers/{schedulerId}")
    public ResponseEntity<SchedulerResponse> updateScheduler(
            @PathVariable Long schedulerId,
            @RequestBody UpdateSchedulerRequest request
    ) {
        return ResponseEntity.ok(schedulerService.updateScheduler(schedulerId, request));
    }

    @DeleteMapping("/schedulers/{schedulerId}")
    public ResponseEntity<Void> deleteScheduler(
            @PathVariable Long schedulerId,
            @RequestBody DeleteSchedulerRequest request
    ) {
        schedulerService.deleteScheduler(schedulerId, request);
        return ResponseEntity.noContent().build();
    }
}
