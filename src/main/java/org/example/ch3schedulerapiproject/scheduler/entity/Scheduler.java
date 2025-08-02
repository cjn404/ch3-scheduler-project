package org.example.ch3schedulerapiproject.scheduler.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Scheduler extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String name;
    private String title;
    private String content;

    public Scheduler(String password, String name, String title, String content) {
        this.password = password;
        this.name = name;
        this.title = title;
        this.content = content;
    }
}
