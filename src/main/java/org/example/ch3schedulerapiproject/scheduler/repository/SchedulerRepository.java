package org.example.ch3schedulerapiproject.scheduler.repository;

import org.example.ch3schedulerapiproject.scheduler.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler,Long> {
}
