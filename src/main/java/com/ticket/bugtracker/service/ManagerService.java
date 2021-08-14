package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Integer saveManager(Manager manager) {

        Set<Ticket> s = manager.getTickets().stream().filter(te -> (te.getEmployee() == null))
                .collect(Collectors.toSet());
        manager.setTickets(s);
        Manager current = managerRepository.save(manager);
        return manager.getID();
    }



}
