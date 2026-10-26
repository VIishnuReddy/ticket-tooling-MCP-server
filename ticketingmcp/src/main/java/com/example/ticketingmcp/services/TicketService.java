package com.example.ticketingmcp.services;

import com.example.ticketingmcp.entities.Ticket;
import com.example.ticketingmcp.repos.TicketRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Transactional
    public Ticket createTicket(
            String username,
            String topic,
            String question,
            String aiSummary) {

        Ticket ticket = new Ticket();
        ticket.setUsername(username);
        ticket.setTopic(topic);
        ticket.setQuestion(question);
        ticket.setAiSummary(aiSummary);
        ticket.setStatus("OPEN");

        return ticketRepo.save(ticket);
    }

    @Transactional(readOnly = true)
    public List<Ticket> getUserTickets(String username) {
        return ticketRepo.findByUsernameOrderByCreatedAtDesc(username);
    }

    @Transactional(readOnly = true)
    public Ticket getTicketById(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Mentor ticket not found: " + id));
    }
}
