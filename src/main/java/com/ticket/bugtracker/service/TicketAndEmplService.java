package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.EmployeeRepository;
import com.ticket.bugtracker.repo.ManagerRepository;
import com.ticket.bugtracker.repo.TicketChecks;
import com.ticket.bugtracker.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketAndEmplService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Integer saveEmpl(Employee employee) {

        Set<Ticket> s = employee.getTickets().stream().filter(te -> (te.getManager() == null))
                .collect(Collectors.toSet()); // checks if the ticket has a manager too to filter it
        employee.setTickets(s);
        Employee current = employeeRepository.save(employee);
        return current.getID();
    }

    public Integer saveManager(Manager manager) {

        Set<Ticket> s = manager.getTickets().stream().filter(te -> (te.getEmployee() == null))
                .collect(Collectors.toSet());
        manager.setTickets(s);
        Manager current = managerRepository.save(manager);
        return manager.getID();
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
}
