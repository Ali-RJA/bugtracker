package com.ticket.bugtracker.repo;


import com.ticket.bugtracker.entity.Ticket;

import java.util.List;
public interface TicketDAO {

    public List<Ticket> findTicketByEmployeeId(Integer id);
    public List<Ticket> findTicketByName(String[] names);
    public List<Ticket> findTicketByName(String name);



}
