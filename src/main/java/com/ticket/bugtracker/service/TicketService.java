package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketService {

    public List<Ticket> findAllById(Iterable<Integer> ids);
    public Integer saveEmpl(Employee employee);
    public Integer saveTicket(Ticket ticket);
    public Integer closeTicket(Ticket ticket);
    public List<Ticket> findTicketsByEmplFirstName(String empName);
    public List<Ticket> findTicketsByDate(Date from, Date to);
    public List<Ticket> findTicketByWildCard(String wc);
    public List<Ticket> findTicketsDefault(int start, int max);
    public List<Ticket> findOpenTicketsOrClosed(boolean open);
    public List<Ticket> findTicketsByCategory(String category);
    }
