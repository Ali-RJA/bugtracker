package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class TicketServiceImpl implements TicketService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private EmployeeDAO employeeDAO;


    public List<Ticket> findAllById(Iterable<Integer> ids) {

        List<Ticket> tickets = ticketRepository.findAllById(ids);

        if (tickets.isEmpty()) {
            System.out.println("NO TICKETS FOUND WITH GIVEN IDs");
        }
        return tickets;
    }

    public Integer saveEmpl(Employee employee) {

        Set<Ticket> s = employee.getTickets().stream().filter(te -> (te.getManager() == null))
                .collect(Collectors.toSet()); // checks if the ticket has a manager too to filter it
        employee.setTickets(s);
        Employee current = employeeRepository.save(employee);
        return current.getID();
    }


    public Integer saveTicket(Ticket ticket) {
        TicketChecks tc = new TicketChecks();
        if (!tc.ticketBothEmpty(ticket) && !tc.ticketBothTaken(ticket)) {
            ticketRepository.save(ticket);
        }
        return ticket.getId();
    }

    @Override
    public List<Integer> saveTickets(List<Ticket> tickets) {
        List<Ticket> current = ticketRepository.saveAll(tickets);
        List<Integer> ids = new ArrayList<>();
        for (Ticket t : current) {
            ids.add(t.getId());
        }
        return ids;
    }

    public Integer closeTicket(Ticket ticket) {
        TicketChecks tc = new TicketChecks();
        if (!tc.ticketBothClosed(ticket)) {
            ticketRepository.save(ticket);
        }
        return ticket.getId();
    }

    public List<Ticket> findTicketsByEmplFirstName(String empName) {
        List<Employee> ids = employeeDAO.findEmployeesIdByName(empName);
        List<Ticket> tickets = ticketDAO.findTicketsByEmployeeIds(ids);
        return tickets;
    }

    @Override
    public List<Ticket> findTicketsByDate(Date from, Date to) {
        return ticketDAO.findTicketsByDate(from, to);
    }

    @Override
    public List<Ticket> findTicketByWildCard(String wc) {
        if (wc.length() > 0) {
            return ticketDAO.findTicketByWildCard(wc);
        }
        else {
            return new ArrayList<Ticket>();
        }
    }

    @Override
    public List<Ticket> findTicketsDefault(int start, int max) {

        return ticketDAO.findTicketsDefault(start, max);
    }

    @Override
    public List<Ticket> findOpenTicketsOrClosed(boolean open) {
        return ticketDAO.findOpenTicketsOrClosed(open);
    }

    @Override
    public List<Ticket> findTicketsByCategory(String category) {
        return ticketDAO.findTicketsByCategory(category);
    }
}
