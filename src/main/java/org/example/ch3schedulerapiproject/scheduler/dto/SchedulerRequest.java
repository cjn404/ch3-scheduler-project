package org.example.ch3schedulerapiproject.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SchedulerRequest {

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
