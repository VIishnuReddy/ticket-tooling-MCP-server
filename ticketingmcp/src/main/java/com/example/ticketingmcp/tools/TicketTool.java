package com.example.ticketingmcp.tools;

import com.example.ticketingmcp.entities.Ticket;
import com.example.ticketingmcp.services.TicketService;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class TicketTool {

    private final TicketService ticketService;

    public TicketTool(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @McpTool(
            name = "create_mentor_ticket",
            description = "Create a mentor support ticket after the learner explicitly confirms the request. The ticket is opened with status OPEN. Creating a ticket does not confirm mentor availability or a booking."
    )
    public String createMentorTicket(
            @McpToolParam(description = "Learner username", required = true)
            String username,

            @McpToolParam(description = "Topic the learner needs help with", required = true)
            String topic,

            @McpToolParam(description = "Learner's question or problem", required = true)
            String question,

            @McpToolParam(description = "Summary of the AI explanation already provided", required = true)
            String aiSummary,

            @McpToolParam(description = "True only after the learner explicitly confirms ticket creation", required = true)
            boolean confirmed) {

        if (!confirmed) {
            return "Ticket not created. Ask the learner to confirm before submitting the request.";
        }

        Ticket ticket = ticketService.createTicket(
                username, topic, question, aiSummary);

        return "Mentor ticket created successfully. "
                + "Ticket ID: " + ticket.getId()
                + ", Status: " + ticket.getStatus()
                + ". This is a request for mentor support, not a confirmed booking.";
    }

    @McpTool(
            name = "get_mentor_ticket_status",
            description = "Retrieve the current status and details of a mentor ticket using its ID."
    )
    public Ticket getMentorTicketStatus(
            @McpToolParam(description = "ID of the mentor ticket", required = true)
            Long ticketId) {

        return ticketService.getTicketById(ticketId);
    }
}
