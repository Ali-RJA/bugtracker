package com.ticket.bugtracker.service;

import com.ticket.bugtracker.entity.Department;
import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Integer saveEmpl(Employee employee);
    public List<Employee> getEmployees();
    public Integer saveManager(Manager manager);
    public Optional<Employee> findById(Integer id);
    public List<Integer> saveEmployees(List<Employee> employees);
    public Integer saveDepartment(Department department);



}
