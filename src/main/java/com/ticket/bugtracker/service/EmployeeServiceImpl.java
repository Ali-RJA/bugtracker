package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.EmployeeRepository;
import com.ticket.bugtracker.repo.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public Integer saveEmpl(Employee employee) {

        Set<Ticket> s = employee.getTickets().stream().filter(te -> (te.getManager() == null))
                .collect(Collectors.toSet()); // checks if the ticket has a manager too to filter it
        employee.setTickets(s);
        Employee current = employeeRepository.save(employee);
        return current.getID();
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
    public Integer saveManager(Manager manager) {

        Set<Ticket> s = manager.getTickets().stream().filter(te -> (te.getEmployee() == null))
                .collect(Collectors.toSet());
        manager.setTickets(s);
        Manager current = managerRepository.save(manager);
        return manager.getID();
    }
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

}
