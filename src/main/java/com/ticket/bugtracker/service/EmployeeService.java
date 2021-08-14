package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

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

    public Optional<Employee> findById(Integer id) {

        return employeeRepository.findById(id);


    }

}
