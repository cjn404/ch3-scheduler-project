package org.example.ch3schedulerapiproject.scheduler.repository;

import org.example.ch3schedulerapiproject.scheduler.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulerRepository extends JpaRepository<Scheduler,Long> {
    // '이름' 기준으로 데이터 찾고, '수정일' 기준으로 '내림차순' 정렬
    // 쿼리 메서드
    List<Scheduler> findByNameOrderByModifiedAtDesc(String name); // 작성자명 있는 경우
    List<Scheduler> findAllByOrderByModifiedAtDesc(); // 작성자명 여부 무방
}
