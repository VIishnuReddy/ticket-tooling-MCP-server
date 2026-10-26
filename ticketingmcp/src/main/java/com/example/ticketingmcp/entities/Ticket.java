package com.example.ticketingmcp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentor_tickets")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, length = 2000)
    private String question;

    @Column(length = 4000)
    private String aiSummary;

    private LocalDateTime requestedSlot;

    @Column(nullable = false)
    private String status = "OPEN";

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Ticket() {
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
