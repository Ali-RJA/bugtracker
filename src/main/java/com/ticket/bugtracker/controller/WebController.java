package com.ticket.bugtracker.controller;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.service.EmployeeService;
import com.ticket.bugtracker.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bugtracker")
public class WebController {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    private final ManagerService managerService;

    public WebController(EmployeeService employeeService, ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    @GetMapping("/findemployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addemployee")
    public ResponseEntity<Integer> addEmployee(@RequestBody Employee employee) {
    Integer id = employeeService.saveEmpl(employee);
    return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @PostMapping("/addmanager")
    public ResponseEntity<Integer> addManager(@RequestBody Manager manager) {
        Integer id = managerService.saveManager(manager);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

   @GetMapping(value = "/findEmployee/{empId}")
   public Employee getEmployee(@PathVariable String empId) {
        Integer userId = Integer.parseInt(empId);
        Employee e = new Employee();
        if (employeeService.findById(userId).isPresent()) {
            return employeeService.findById(userId).get();
        }
        return e;
   }


}
