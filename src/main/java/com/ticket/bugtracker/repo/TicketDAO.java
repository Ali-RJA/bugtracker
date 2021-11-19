package com.ticket.bugtracker.repo;


import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public interface TicketDAO {

    public List<Ticket> findTicketsByEmployeeIds(List<Employee> employees);
    public List<Ticket> findTicketsByDate(Date from, Date to);
    public List<Ticket> findTicketByWildCard(String wc);
    public List<Ticket> findTicketsDefault(int start, int max);
    public List<Ticket> findOpenTicketsOrClosed(boolean open);


    public List<Ticket> findTicketsByCategory(String category);
}
