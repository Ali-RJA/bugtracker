package com.ticket.bugtracker.repo;

import com.ticket.bugtracker.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public Integer findEmployeeIdByName(String name);
    public List<Employee> findEmployeesIdByName(String name);

}
