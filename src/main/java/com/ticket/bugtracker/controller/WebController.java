package com.ticket.bugtracker.controller;

import com.ticket.bugtracker.entity.Department;
import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.service.EmployeeService;
import com.ticket.bugtracker.service.TicketService;
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
    private final TicketService ticketService;

    public WebController(EmployeeService employeeService, TicketService ticketService) {
        this.employeeService = employeeService;
        this.ticketService = ticketService;
    }

    @GetMapping("/findemployees") // finds employee
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addemployee") // adds employee to the database if valid
    public ResponseEntity<Integer> addEmployee(@RequestBody Employee employee) {
    Integer id = employeeService.saveEmpl(employee);
    return new ResponseEntity<>(id, HttpStatus.CREATED);

    }
    @PostMapping("/addemployees") // adds list employees for testing mainly (data population)
    public ResponseEntity<List<Integer>> addEmployees(@RequestBody List<Employee> employees) {
        List<Integer> ids = employeeService.saveEmployees(employees);
        return new ResponseEntity<>(ids, HttpStatus.CREATED);
    }
    @PostMapping("/addmanager")
    public ResponseEntity<Integer> addManager(@RequestBody Manager manager) {
        Integer id = employeeService.saveManager(manager);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @PostMapping("/adddepartment")
    public ResponseEntity<Integer> addDepartment(@RequestBody Department department) {
        Integer id = employeeService.saveDepartment(department);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

   @GetMapping(value = "/findemployee/{empId}")
   public Employee getEmployee(@PathVariable String empId) {
        Integer userId = Integer.parseInt(empId);
        Employee e = new Employee();
        if (employeeService.findById(userId).isPresent()) {
            return employeeService.findById(userId).get();
        }
        return e;
   }
    @PostMapping("/addticket")
    public ResponseEntity<Integer> addTicket(@RequestBody Ticket ticket) {
        Integer id = ticketService.saveTicket(ticket);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    @PostMapping("/addtickets")
    public ResponseEntity<List<Integer>> addTicket(@RequestBody List<Ticket> tickets) {
        List<Integer> ids = ticketService.saveTickets(tickets);
        return new ResponseEntity<>(ids, HttpStatus.CREATED);
    }
    @GetMapping(value = "/findtickets/{empName}")
    public ResponseEntity<List<Ticket>> findTicketsByEmployeeName(@PathVariable String empName) {
        System.out.println("NAME IS : ---->  " + empName);
        List<Ticket> tickets = ticketService.findTicketsByEmplFirstName(empName);
        tickets.forEach(e -> {
            System.out.println("TICKET TITLE: --> "+e.getTitle());
        });
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }



}
