package com.example.ticketingmcp.repos;

import com.example.ticketingmcp.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUsernameOrderByCreatedAtDesc(
            String username);
}
