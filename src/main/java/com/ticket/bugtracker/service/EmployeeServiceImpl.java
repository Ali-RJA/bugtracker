package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Department;
import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.DepartmentRepository;
import com.ticket.bugtracker.repo.EmployeeRepository;
import com.ticket.bugtracker.repo.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private DepartmentRepository departmentRepository;

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

    @Override
    public List<Integer> saveEmployees(List<Employee> employees) {
        List<Integer> integers = new ArrayList<>();
        List<Employee> employeesSaved = employeeRepository.saveAll(employees);
        for (Employee e : employeesSaved) {
            integers.add(saveEmpl(e));
        }
        return integers;
    }

    @Override
    public Integer saveDepartment(Department department) {
        Department current = departmentRepository.save(department);
        return current.getId();
    }

}
