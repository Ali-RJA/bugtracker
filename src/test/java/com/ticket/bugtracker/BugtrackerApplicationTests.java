package com.ticket.bugtracker;

import com.ticket.bugtracker.entity.Department;
import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Manager;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.service.TicketAndEmplService;
import com.ticket.bugtracker.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class BugtrackerApplicationTests {

    @Autowired
    TicketAndEmplService ticketAndEmplService;

    @Autowired
    TicketService ticketService;

    @Test
    void contextLoads() {
    }

    private String a = "Mohamed";
    private String b = "Yang";
    private String c = "Malissa";
    private String d = "LilDurk";

    private String aa = "Jasim";
    private String bb = "Chyao";
    private String cc = "Smith";
    private String dd = "Smurk";

    private String w = "mohamed@gmail.com";
    private String x = "yang@yahoo.com";
    private String y = "malissa94@outlook.com";
    private String z = "lildurkiosmurkio@gmail.com";

    private Department department = new Department("Support");








  /* @Test
    void addEmployees() {
        Employee ali = new Employee();
        ali.setEmail("ali@gmail.com");
        ali.setFirstName("Alisa");
        ali.setLastName("Alabbasi");
       Manager jon = new Manager();
       jon.setEmail("jon@gmail.com");
       jon.setFirstName("Jon");
       jon.setLastName("Doe");

        Ticket cat = new Ticket();
        cat.setTitle("OMG my cat escaped!");
        cat.setCategory("cat");
        cat.setDate(new Date());
        cat.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN" );
        cat.setPriority(8);

        Ticket dog = new Ticket();
        dog.setTitle("My dog is a ");
        dog.setCategory("dog");
        dog.setDate(new Date());
        dog.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN" );
        dog.setPriority(4);

       Set<Ticket> tickets = new HashSet<>();
      tickets.add(dog);
      tickets.add(cat);
      dog.setEmployee(ali);
      cat.setEmployee(ali);
      cat.setManager(jon);
      ali.setTickets(tickets);
      ticketAndEmplService.saveEmpl(ali);
      //  Integer aliId = ticketAndEmplService.saveEmpl(ali);
    }

    /*@Test
    void TicketExistsByManager() {
        Employee ali = new Employee();
        ali.setEmail("ali@gmail.com");
        ali.setFirstName("Alisa");
        ali.setLastName("Alabbasi");

        Manager jon = new Manager();
        jon.setEmail("jon@gmail.com");
        jon.setFirstName("Jon");
        jon.setLastName("Doe");

        Ticket cat = new Ticket();
        cat.setTitle("OMG my cat escaped!");
        cat.setCategory("cat");
        cat.setDate(new Date());
        cat.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN" );
        cat.setPriority(8);

        Ticket dog = new Ticket();
        dog.setTitle("My dog is a dog!");
        dog.setCategory("dog");
        dog.setDate(new Date());
        dog.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN" );
        dog.setPriority(4);

        cat.setEmployee(ali);
       // dog.setManager(jon);
        dog.setEmployee(ali);
        ali.addTicket(cat);
        ali.addTicket(dog);
        ticketAndEmplService.saveEmpl(ali);
        Assertions.assertTrue(1 == 1);
    }*/

    Department getDepartment() {
        Department department = new Department("Support");
        return department;
    }

    @Test
    Employee addEmployeeNoDept() {
        Employee ali = new Employee();
        ali.setEmail("ali@gmail.com");
        ali.setFirstName("Alisa");
        ali.setLastName("Alabbasi");
        ali.setDepartment(getDepartment());
        ticketAndEmplService.saveEmpl(ali);
        return ali;
    }

    @Test
    Manager addManagerNoDept() {
        Manager ali = new Manager();
        ali.setEmail("jon@gmail.com");
        ali.setFirstName("Jon");
        ali.setLastName("Kaka");
        ali.setDepartment(getDepartment());
        ticketAndEmplService.saveManager(ali);
        return ali;
    }


    @Test
    Employee addEmployeeWithDept() {
        Manager x = addManagerWithDept();

        Employee ali = new Employee();
        ali.setManager(x);
        ali.setEmail(w);
        ali.setFirstName(a);
        ali.setLastName(aa);
        ali.setDepartment(department);
        ticketAndEmplService.saveEmpl(ali);
        return ali;
    }

    @Test
    Manager addManagerWithDept() {
        Manager ali = new Manager();
        ali.setEmail(x);
        ali.setFirstName(b);
        ali.setLastName(bb);
        ali.setDepartment(department);
        department.setManager(ali);
        ticketAndEmplService.saveManager(ali);
        return ali;
    }
    /*
    1. Add ticket with employee
    2. Add ticket with Manager
    3. Add ticket with neither
    4. Add ticket with both
    5. Close ticket with Employee
    6. Close ticket with manager
    7. Close ticket with both closers
    8.
     */


    @Test
    void closeTicketEmpl() {
        Employee ali = addEmployeeWithDept();
        Ticket cat = new Ticket();
        cat.setTitle("OMG my cat escaped!");
        cat.setCategory("cat");
        cat.setDate(new Date());
        cat.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        cat.setPriority(8);
        cat.setEmployee(ali);
        ticketAndEmplService.saveEmpl(ali);
        ticketAndEmplService.saveTicket(cat);
        Employee jj = new Employee();
        jj.setFirstName(c);
        jj.setLastName(cc);
        jj.setEmail(y);
        jj.setDepartment(ali.getDepartment());
        ticketAndEmplService.saveEmpl(jj);
        cat.setEmployeeCloser(jj);
        ticketAndEmplService.closeTicket(cat);

    }

    @Test
    void closeTicketWithManager() {
        Employee ali = addEmployeeWithDept();
        Ticket cat = new Ticket();
        cat.setTitle("OMG my cat escaped!");
        cat.setCategory("cat");
        cat.setDate(new Date());
        cat.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        cat.setPriority(8);
        cat.setEmployee(ali);
        ticketAndEmplService.saveEmpl(ali);
        ticketAndEmplService.saveTicket(cat);
        Manager yoyo = new Manager();
        yoyo.setFirstName(c);
        yoyo.setLastName(cc);
        yoyo.setEmail(y);
        Department x = new Department("Coco");
        x.setManager(yoyo);
        yoyo.setDepartment(x);
        ticketAndEmplService.saveManager(yoyo);

        cat.setManagerCloser(yoyo);
        ticketAndEmplService.closeTicket(cat);
    }

    @Test
    void closeTicketWithBoth() {
        Employee ali = addEmployeeWithDept();
        Ticket cat = new Ticket();
        cat.setTitle("OMG my cat escaped!");
        cat.setCategory("cat");
        cat.setDate(new Date());
        cat.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        cat.setPriority(8);
        cat.setEmployee(ali);
        ticketAndEmplService.saveEmpl(ali);
        ticketAndEmplService.saveTicket(cat);
        Manager yoyo = new Manager();
        yoyo.setFirstName(c);
        yoyo.setLastName(cc);
        yoyo.setEmail(y);
        Department x = new Department("Coco");
        x.setManager(yoyo);
        yoyo.setDepartment(x);
        ticketAndEmplService.saveManager(yoyo);
        cat.setManagerCloser(yoyo);
        Employee jj = new Employee();
        jj.setFirstName(c);
        jj.setLastName(cc);
        jj.setEmail(y);
        jj.setDepartment(ali.getDepartment());
        ticketAndEmplService.saveEmpl(jj);
        cat.setEmployeeCloser(jj);
        ticketAndEmplService.closeTicket(cat);

    }

    @Test
    void addTicketWithEmployee() {
        Employee ali = addEmployeeWithDept();
        Ticket cat = new Ticket();
        cat.setTitle("OMG my cat escaped!");
        cat.setCategory("cat");
        cat.setDate(new Date());
        cat.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        cat.setPriority(8);
        cat.setEmployee(ali);
        Ticket dog = new Ticket();
        dog.setTitle("My dog's a dog!");
        dog.setDate(new Date());
        dog.setCategory("dog");
        dog.setDescription("I can't belive that my dog really escaped!");
        dog.setPriority(6);
        dog.setEmployee(ali);
        //ticketAndEmplService.saveEmpl(ali);
        ticketAndEmplService.saveTicket(cat);
        ticketAndEmplService.saveTicket(dog);
        List<Integer> ids = new ArrayList<>();
        ids.add(cat.getId());
        List<Ticket> tickets = ticketsById(ids);
        tickets.forEach(e -> System.out.println(e.getTitle()));
    }

    @Test
    void addTicketWithManager() {
        Manager ali = addManagerWithDept();
        Ticket dog = new Ticket();
        dog.setTitle("My dog is a dog!");
        dog.setCategory("dog");
        dog.setDate(new Date());
        dog.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        dog.setPriority(4);

        dog.setManager(ali);
        dog.setDepartment(ali.getDepartment());
        ticketAndEmplService.saveManager(ali);
        ticketAndEmplService.saveTicket(dog);

    }

    @Test
    void addTicketWithNeither() {
        Ticket dog = new Ticket();
        dog.setTitle("My dog is a dog!");
        dog.setCategory("dog");
        dog.setDate(new Date());
        dog.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        dog.setPriority(4);
        ticketAndEmplService.saveTicket(dog);
    }

    @Test
    void addTicketWithBoth() {
        Department department = new Department("Support");
        Employee ali = new Employee();
        ali.setEmail("ali@gmail.com");
        ali.setFirstName("Alisa");
        ali.setLastName("Alabbasi");
        ali.setDepartment(department);
        Manager j = new Manager();
        j.setEmail("jon@gmail.com");
        j.setFirstName("Jon");
        j.setLastName("Kaka");
        j.setDepartment(department);
        ticketAndEmplService.saveManager(j);
        ticketAndEmplService.saveEmpl(ali);
        Ticket dog = new Ticket();
        dog.setTitle("My dog is a dog!");
        dog.setCategory("dog");
        dog.setDate(new Date());
        dog.setDescription("IKJ SDA dsfkjgndkgfj SDNKJN KJND SAA" +
                "sdfkjnsdkjf  sdf jklhsdf kjdshfkjdshfdkjsfhKJDSKJFN");
        dog.setPriority(4);
        dog.setManager(j);
        dog.setEmployee(ali);
        ticketAndEmplService.saveTicket(dog);
    }

    List<Ticket> ticketsById(Iterable<Integer> ids) {

        List<Ticket> tickets = ticketService.findAllById(ids);
        return tickets;
    }


}
