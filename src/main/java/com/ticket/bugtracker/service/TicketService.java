package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.TicketChecks;
import com.ticket.bugtracker.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {


    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Integer saveTicket(Ticket ticket) {
        TicketChecks tc = new TicketChecks();
        if (!tc.ticketBothEmpty(ticket) && !tc.ticketBothTaken(ticket)) {
            ticketRepository.save(ticket);
        }
        return ticket.getId();
    }

    public Integer closeTicket(Ticket ticket) {
        TicketChecks tc = new TicketChecks();
        if (!tc.ticketBothClosed(ticket)) {
            ticketRepository.save(ticket);

        }
        return ticket.getId();
    }

    public List<Ticket> findAllById(Iterable<Integer> ids) {

       List<Ticket> tickets = ticketRepository.findAllById(ids);

       if (tickets.isEmpty()) {
       System.out.println("NO TICKETS FOUND WITH GIVEN IDs");
       }
       return tickets;
    }



}
