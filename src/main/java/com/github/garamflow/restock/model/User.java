package com.github.garamflow.restock.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private LocalDateTime createdAt;

    public User(String username) {
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }

    protected User() {}
}
